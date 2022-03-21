package editor.referencelistHandler;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiReferenceList;

import java.util.Objects;

public class ExtendListHandler extends ReferenceListHandler{
    public ExtendListHandler(Project project, PsiElementFactory factory, PsiClass psiClass) {
        super(project, factory, psiClass);
    }

    @Override
    public void updateReferenceList(String ext) {
        PsiReferenceList extendsList = psiClass.getExtendsList();

        var referenceElement = factory
                .createReferenceElementByFQClassName("java.lang." + ext, psiClass.getResolveScope());

        WriteCommandAction.runWriteCommandAction(project, () -> {
            Objects.requireNonNull(extendsList).add(referenceElement);
        });

    }
}
