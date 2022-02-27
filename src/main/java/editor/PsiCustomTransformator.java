package editor;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import exception.PsiManipulateException;


public class PsiCustomTransformator {
    private final PsiElementFactory psiElementFactory;
    private final PsiElement caretPsiElement;
    private final Project project;

    public PsiCustomTransformator(Project project, Editor editor) {
        this.project = project;
        this.psiElementFactory = JavaPsiFacade.getInstance(project).getElementFactory();
        this.caretPsiElement = PsiUtilBase.getElementAtCaret(editor);
    }

    public boolean doTransform(String insertion) {
        PsiClass psiClass = PsiTreeUtil.getParentOfType(caretPsiElement, PsiClass.class);
        PsiReferenceList referenceList = getPsiReferenceList(psiClass);

        insertNewImplementInterface(insertion, referenceList);

        return false;
    }

    private PsiReferenceList getPsiReferenceList(PsiClass psiClass) {
        if (psiClass != null) {
            return psiClass.getImplementsList();
        } else {
            throw new PsiManipulateException("Error while extracting the PsiClass node");
        }
    }


    private void insertNewImplementInterface(String insertion, PsiReferenceList referenceList) {
        PsiElement comma = psiElementFactory.createExpressionFromText("f(a,b)", caretPsiElement)
                .getLastChild()
                .getChildren()[2];

        int length = referenceList.getReferenceElements().length;

        WriteCommandAction.runWriteCommandAction(project, () -> {
            try {
                if (length > 0) {
                    referenceList.add(comma);
                }
            } catch (IllegalArgumentException exception) {
                //skip this exception due to unknown exception
            }
            referenceList.add(psiElementFactory.createTypeElementFromText(insertion, caretPsiElement));
        });
    }
}
