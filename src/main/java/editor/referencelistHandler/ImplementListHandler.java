package editor.referencelistHandler;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiReferenceList;

import java.util.Objects;

public class ImplementListHandler extends ReferenceListHandler{
    public ImplementListHandler(Project project, PsiElementFactory factory, PsiClass psiClass) {
        super(project, factory, psiClass);
    }

    @Override
    public void updateReferenceList(String impl) {
        PsiReferenceList implementsList = psiClass.getImplementsList();

        var referenceElement = factory
                .createReferenceElementByFQClassName("java.lang." + impl, psiClass.getResolveScope());

        WriteCommandAction.runWriteCommandAction(project, () -> {
            Objects.requireNonNull(implementsList).add(referenceElement);
        });
    }
}
