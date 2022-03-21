package editor;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;

import java.util.List;


public class PsiCustomTransformator {
    public static void doTransform(List<String> insertion, String keyword, PsiClass psiClass, Project project) {
        CustomAction action = new CustomAction(project, psiClass);

        action.insertNewElements(insertion, keyword);
    }
}
