package editor;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import editor.referencelistHandler.ExtendListHandler;
import editor.referencelistHandler.ImplementListHandler;
import editor.referencelistHandler.ReferenceListHandler;

import java.util.List;


public class CustomAction {
    private final PsiElementFactory psiElementFactory;
    private final Project project;
    private final PsiClass psiClass;

    public CustomAction(Project project, PsiClass psiClass) {
        this.psiElementFactory = PsiElementFactory.getInstance(project);
        this.project = project;
        this.psiClass = psiClass;
    }

    public void insertNewElements(List<String> insertion, String type) {
        ReferenceListHandler referenceListHandler;

        if (type.equals("IMPL")) {
            referenceListHandler = new ImplementListHandler(project, psiElementFactory, psiClass);

            insertion.forEach(referenceListHandler::updateReferenceList);
        }
        else if (type.equals("EXT")) {
            referenceListHandler = new ExtendListHandler(project, psiElementFactory, psiClass);

            insertion.forEach(referenceListHandler::updateReferenceList);
        }
        else {
            throw new IllegalArgumentException("insertion type error");
        }
    }
}
