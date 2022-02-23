import com.intellij.lang.ASTNode;
import com.intellij.lang.DefaultASTFactoryImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.Factory;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.java.IJavaElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.util.CharTable;
import com.intellij.util.IncorrectOperationException;
import groovy.util.logging.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class ComfColon extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        PsiElementFactory factory = JavaPsiFacade.getInstance(event.getData(CommonDataKeys.PROJECT)).getElementFactory();
        //PsiUtil.= JavaPsiFacade.getInstance(event.getData(CommonDataKeys.PROJECT)).getElementFactory();

        //TODO do not add any comma if interfaces arent implemented
        //TODO refactor this code
        //TODO add extends logic
        //TODO refactor future code

        if (psiFile != null) {
            PsiElement psiElement = PsiUtilBase.getElementAtCaret(event.getData(CommonDataKeys.EDITOR));
            var psiClass = PsiTreeUtil.getParentOfType(psiElement, PsiClass.class);
            PsiReferenceList implementsList = psiClass.getImplementsList();

            if(implementsList != null){
                CharTable charTableByTree = SharedImplUtil.findCharTableByTree(psiClass.getNode());
                //3 element is comma
                PsiElement comma = factory.createExpressionFromText("f(a,b)", psiElement).getLastChild().getChildren()[2];
                System.out.println(comma.toString());
                WriteCommandAction.runWriteCommandAction(event.getProject(), () -> {
                    try {
                        implementsList
                                //.add(Factory.createSingleLeafElement(JavaTokenType.COMMA, ",", charTableByTree, psiElement.getManager()))
                                .add(comma);
                    } catch (IncorrectOperationException e) {
                        //linux style
                    }
                    implementsList.add(factory.createTypeElementFromText("Runnable", psiElement));
                });
            }else {

            }


            System.out.println(psiElement.toString());
        }


        System.out.println("here");

    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }


}
