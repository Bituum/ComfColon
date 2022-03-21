package editor.referencelistHandler;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;

public abstract class ReferenceListHandler {
    protected Project project;
    protected PsiElementFactory factory;
    protected PsiClass psiClass;

    public ReferenceListHandler(Project project, PsiElementFactory factory, PsiClass psiClass) {
        this.project = project;
        this.factory = factory;
        this.psiClass = psiClass;
    }

    public abstract void updateReferenceList(String tail);
}
