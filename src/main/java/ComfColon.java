import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import editor.PsiCustomTransformator;
import org.jetbrains.annotations.NotNull;

public class ComfColon extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        PsiCustomTransformator editor = new PsiCustomTransformator(event.getProject(), event.getData(CommonDataKeys.EDITOR));
        boolean isWorkDoneWell = editor.doTransform("Runnable");
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }


}
