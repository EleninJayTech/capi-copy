package org.example.actions;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.example.settings.CopyPluginSettings;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Locale;
import java.util.ResourceBundle;

public class CopyFilePathAndContentAction extends AnAction {

    private long lastClickedTime = 0; // 마지막 클릭 시간 기록
    private static final long DOUBLE_CLICK_THRESHOLD_MS = 500; // 더블 클릭 기준 (0.5초)
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("messages.messages", Locale.getDefault());

    private String getLanguageFromFileName(String fileName) {
        if (fileName.endsWith(".java")) return "java";
        if (fileName.endsWith(".kt")) return "kotlin";
        if (fileName.endsWith(".xml")) return "xml";
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) return "html";
        if (fileName.endsWith(".css")) return "css";
        if (fileName.endsWith(".scss")) return "scss";
        if (fileName.endsWith(".js")) return "javascript";
        if (fileName.endsWith(".jsx")) return "jsx";
        if (fileName.endsWith(".ts")) return "typescript";
        if (fileName.endsWith(".tsx")) return "tsx";
        if (fileName.endsWith(".py")) return "python";
        if (fileName.endsWith(".rb")) return "ruby";
        if (fileName.endsWith(".php")) return "php";
        if (fileName.endsWith(".go")) return "go";
        if (fileName.endsWith(".rs")) return "rust";
        if (fileName.endsWith(".c")) return "c";
        if (fileName.endsWith(".cpp") || fileName.endsWith(".cc") || fileName.endsWith(".cxx") || fileName.endsWith(".hpp") || fileName.endsWith(".h")) return "cpp";
        if (fileName.endsWith(".cs")) return "csharp";
        if (fileName.endsWith(".swift")) return "swift";
        if (fileName.endsWith(".dart")) return "dart";
        if (fileName.endsWith(".sql")) return "sql";
        if (fileName.endsWith(".json")) return "json";
        if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) return "yaml";
        if (fileName.endsWith(".sh")) return "bash";
        if (fileName.endsWith(".md")) return "markdown";
        if (fileName.endsWith(".gradle")) return "gradle";
        if (fileName.endsWith(".properties")) return "properties";
        if (fileName.endsWith(".ini")) return "ini";
        if (fileName.endsWith(".dockerfile")) return "dockerfile";
        if (fileName.endsWith(".makefile")) return "makefile";
        if (fileName.endsWith(".bat")) return "batch";
        if (fileName.endsWith(".ps1")) return "powershell";
        return "";
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            showNotification(BUNDLE.getString("notification.no.project"), NotificationType.ERROR, null);
            return;
        }

        long currentTime = System.currentTimeMillis();
        boolean isDoubleClick = (currentTime - lastClickedTime) < DOUBLE_CLICK_THRESHOLD_MS;
        lastClickedTime = currentTime;

        StringBuilder clipboardText = new StringBuilder();

        // 공통 포맷 정의
        String fileCopyFormat = "--- START FILE ---\nFile Path: %s\n\n```%s\n%s\n```\n--- END FILE ---\n\n";
        String selectionCopyFormat = "--- START FILE ---\nFile Path: %s:%d-%d\n\n```%s\n%s\n```\n--- END FILE ---\n\n";

        if (isDoubleClick) {
            // 빠르게 두 번 클릭: 항상 모든 열린 파일 복사
            VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
            if (openFiles.length == 0) {
                showNotification(BUNDLE.getString("notification.no.open.files"), NotificationType.ERROR, project);
                return;
            }

            for (VirtualFile file : openFiles) {
                Document document = FileDocumentManager.getInstance().getDocument(file);
                if (document == null) continue;

                String filePath = file.getPath();
                String fileName = file.getName();
                String fileContent = document.getText();
                String language = getLanguageFromFileName(fileName);

                clipboardText.append(String.format(
                        fileCopyFormat,
                        filePath, language, fileContent));
            }

            showNotification(BUNDLE.getString("notification.copied.all"), NotificationType.INFORMATION, project);
        } else {
            // 한번 클릭: 기존 설정된 옵션으로 복사
            String copyOption = CopyPluginSettings.getInstance().getCopyOption();

            if ("all_open_files".equals(copyOption)) {
                VirtualFile[] openFiles = FileEditorManager.getInstance(project).getOpenFiles();
                if (openFiles.length == 0) {
                    showNotification(BUNDLE.getString("notification.no.open.files"), NotificationType.ERROR, project);
                    return;
                }

                for (VirtualFile file : openFiles) {
                    Document document = FileDocumentManager.getInstance().getDocument(file);
                    if (document == null) continue;

                    String filePath = file.getPath();
                    String fileName = file.getName();
                    String fileContent = document.getText();
                    String language = getLanguageFromFileName(fileName);

                    clipboardText.append(String.format(
                            fileCopyFormat,
                            filePath, language, fileContent));
                }

                showNotification(BUNDLE.getString("notification.copied.all"), NotificationType.INFORMATION, project);
            } else {
                // path_and_content 또는 selection_or_all
                Editor editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR);
                if (editor == null) {
                    showNotification(BUNDLE.getString("notification.no.open.files"), NotificationType.ERROR, project);
                    return;
                }

                Document document = editor.getDocument();
                VirtualFile file = FileDocumentManager.getInstance().getFile(document);
                if (file == null) {
                    showNotification(BUNDLE.getString("notification.no.file"), NotificationType.ERROR, project);
                    return;
                }

                String filePath = file.getPath();
                String fileName = file.getName();
                String language = getLanguageFromFileName(fileName);

                if ("selection_or_all".equals(copyOption)) {
                    String selectedText = editor.getSelectionModel().getSelectedText();
                    if (selectedText != null && !selectedText.isEmpty()) {
                        // 선택된 텍스트가 있을 경우, 시작 및 마지막 줄 번호 포함
                        int startOffset = editor.getSelectionModel().getSelectionStart();
                        int endOffset = editor.getSelectionModel().getSelectionEnd();
                        int startLine = document.getLineNumber(startOffset) + 1; // 0-based to 1-based
                        int endLine = document.getLineNumber(endOffset) + 1; // 0-based to 1-based

                        clipboardText.append(String.format(
                                selectionCopyFormat,
                                filePath, startLine, endLine, language, selectedText));
                    } else {
                        // 선택된 텍스트가 없을 경우, 전체 내용 복사
                        String fileContent = document.getText();
                        clipboardText.append(String.format(
                                fileCopyFormat,
                                filePath, language, fileContent));
                    }
                } else {
                    // 기본 path_and_content
                    String fileContent = document.getText();
                    clipboardText.append(String.format(
                            fileCopyFormat,
                            filePath, language, fileContent));
                }

                showNotification(BUNDLE.getString("notification.copied.single"), NotificationType.INFORMATION, project);
            }
        }

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(clipboardText.toString()), null);
    }

    private void showNotification(String message, NotificationType type, Project project) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("Custom Notification Group")
                .createNotification(message, type)
                .notify(project);
    }
}