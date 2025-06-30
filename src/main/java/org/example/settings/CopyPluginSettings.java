package org.example.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.Service;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "CopyPluginSettings",
        storages = @Storage("CopyPluginSettings.xml")
)
@Service(Service.Level.APP)
public final class CopyPluginSettings implements PersistentStateComponent<CopyPluginSettings.State> {

    public static class State {
        public String copyOption = "selection_or_all";
    }

    private State myState = new State();

    @Override
    public @Nullable State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.myState.copyOption = state.copyOption;
    }

    public static CopyPluginSettings getInstance() {
        return ApplicationManager.getApplication().getService(CopyPluginSettings.class);
    }

    public String getCopyOption() {
        return myState.copyOption;
    }

    public void setCopyOption(String option) {
        myState.copyOption = option;
        ApplicationManager.getApplication().invokeLater(() -> {
            ApplicationManager.getApplication().saveSettings();
        });
    }
}