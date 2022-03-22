package invoker;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import dialogUtil.ConfColonBox;
import editor.PsiCustomTransformator;
import util.Keyword;

import java.util.List;

public class Invoker {
    private final PsiClass psiClass;
    private final Project project;
    private List<String> implList;
    private List<String> extList;
    private boolean implSelected;
    private boolean extSelected;

    public Invoker(PsiClass psiClass, Project project) {
        this.psiClass = psiClass;
        this.project = project;
    }

    public void invoke(List<List<String>> lists) {
        this.implSelected = ConfColonBox.isImplSelected();
        this.extSelected = ConfColonBox.isExtSelected();

        extractList(lists);

        if (implSelected && extSelected) {
            PsiCustomTransformator.doTransform(implList, Keyword.IMPLEMENTS, psiClass, project);
            PsiCustomTransformator.doTransform(extList, Keyword.EXTENDS, psiClass, project);
        }
        else if (implSelected) {
            PsiCustomTransformator.doTransform(implList, Keyword.IMPLEMENTS, psiClass, project);
        }
        else {
            PsiCustomTransformator.doTransform(extList, Keyword.EXTENDS, psiClass, project);
        }
    }

    private void extractList(List<List<String>> lists) {
        if (implSelected && extSelected) {
            implList = lists.get(0);
            extList = lists.get(1);
        }
        else if (implSelected) {
            implList = lists.get(0);
        }
        else {
            extList = lists.get(1);
        }
    }
}
