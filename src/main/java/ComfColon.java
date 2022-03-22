import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import dialogUtil.ConfColonBox;
import invoker.Invoker;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class ComfColon extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        PsiElement elementAtCaret = PsiUtilBase.getElementAtCaret(Objects.requireNonNull(editor));
        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAtCaret, PsiClass.class);
        ConfColonBox form = new ConfColonBox(project, true);
        Invoker invoker = new Invoker(psiClass, project);

        form.show();

        try {
            invoker.invoke(form.takeResult());
        } catch (IllegalStateException exception) {
            exception.getMessage();
        }
        System.out.println(form.getExitCode());

    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
