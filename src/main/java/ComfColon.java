import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import editor.PsiCustomTransformator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComfColon extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        PsiElement elementAtCaret = PsiUtilBase.getElementAtCaret(Objects.requireNonNull(editor));
        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAtCaret, PsiClass.class);

        List<String> implInsertions = new ArrayList<>();
        implInsertions.add("Runnable");
        implInsertions.add("Random");
        implInsertions.add("Readable");

        List<String> extInsertions = new ArrayList<>();
        extInsertions.add("AnAction");

        PsiCustomTransformator.doTransform(implInsertions, "IMPL", psiClass, project);
        PsiCustomTransformator.doTransform(extInsertions, "EXT", psiClass, project);
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }


}
