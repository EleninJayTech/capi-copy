package org.example.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
public class CopyPluginConfigurable implements Configurable {

    private JRadioButton pathAndContentButton;
    private JPanel settingsPanel;
    private JRadioButton allOpenFilesButton;
    private JRadioButton selectionOrAllButton;
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("messages.messages", Locale.getDefault());

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Capi Copy Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createTitledBorder(BUNDLE.getString("copy.option.group")));
        settingsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descriptionLabel = new JLabel(BUNDLE.getString("copy.option.label"));
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 12, 4, 0));
        settingsPanel.add(descriptionLabel);

        pathAndContentButton = new JRadioButton(BUNDLE.getString("option.path_and_content"));
        selectionOrAllButton = new JRadioButton(BUNDLE.getString("option.selection_or_all"));
        allOpenFilesButton = new JRadioButton(BUNDLE.getString("option.all_open_files"));

        pathAndContentButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectionOrAllButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        allOpenFilesButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        group.add(pathAndContentButton);
        group.add(selectionOrAllButton);
        group.add(allOpenFilesButton);

        settingsPanel.add(Box.createVerticalStrut(4));  // 간격 조정
        settingsPanel.add(pathAndContentButton);
        settingsPanel.add(Box.createVerticalStrut(2));
        settingsPanel.add(selectionOrAllButton);
        settingsPanel.add(Box.createVerticalStrut(2));
        settingsPanel.add(allOpenFilesButton);
        settingsPanel.add(Box.createVerticalGlue());

        reset(); // 설정 값 로드

        return settingsPanel;
    }

    private JPanel wrapButton(JRadioButton button) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(2, 12, 2, 0)); // 간격 조절
        panel.add(button, BorderLayout.WEST);
        return panel;
    }

    @Override
    public boolean isModified() {
        CopyPluginSettings settings = CopyPluginSettings.getInstance();
        return !Objects.equals(getSelectedOption(), settings.getCopyOption());
    }

    @Override
    public void apply() {
        CopyPluginSettings settings = CopyPluginSettings.getInstance();
        settings.setCopyOption(getSelectedOption());
    }

    @Override
    public void reset() {
        CopyPluginSettings settings = CopyPluginSettings.getInstance();
        switch (settings.getCopyOption()) {
            case "all_open_files":
                allOpenFilesButton.setSelected(true);
                break;
            case "selection_or_all":
                selectionOrAllButton.setSelected(true);
                break;
            default:
                pathAndContentButton.setSelected(true);
                break;
        }
    }

    private String getSelectedOption() {
        if (allOpenFilesButton.isSelected()) return "all_open_files";
        if (selectionOrAllButton.isSelected()) return "selection_or_all";
        return "path_and_content";
    }
}
