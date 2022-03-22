package dialogUtil;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ConfColonBox extends DialogWrapper {
    private static boolean implButton = false;
    private static boolean extButton = false;
    private final JPanel dialogPanel = new JPanel(new BorderLayout());

    public ConfColonBox(@Nullable Project project, boolean canBeParent) {
        super(project, canBeParent);
        setTitle("COMF COLON");
        init();
    }

    public static void setImplSelected() {
        implButton = true;
    }

    public static void setExtendSelected() {
        extButton = true;
    }

    public static boolean isImplSelected() {
        return implButton;
    }

    public static boolean isExtSelected() {
        return implButton;
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JPanel checkboxPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Выберите, что вы хотите добавить");

        JTextField implementsInput = new JTextField(20);
        JTextField extendsInput = new JTextField(20);

        inputPanel.add(implementsInput, BorderLayout.WEST);
        inputPanel.add(extendsInput, BorderLayout.EAST);

        JCheckBox implBox = new JCheckBox("Implement");
        JCheckBox extBox = new JCheckBox("Extends");
        checkboxPanel.add(implBox, BorderLayout.WEST);
        checkboxPanel.add(extBox, BorderLayout.EAST);

        label.setPreferredSize(new Dimension(300, 250));
        this.dialogPanel.add(label, BorderLayout.NORTH);
        this.dialogPanel.add(inputPanel, BorderLayout.CENTER);
        this.dialogPanel.add(checkboxPanel, BorderLayout.SOUTH);

        return this.dialogPanel;
    }

    public List<List<String>> takeResult() {
        List<JTextField> textFields = new ArrayList<>();
        List<JCheckBox> checkBoxes = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();

        var components = Util.getAllComponents(dialogPanel);

        for (Component component : components) {
            if (component instanceof JTextField) {
                textFields.add((JTextField) component);
            }
            if (component instanceof JCheckBox) {
                checkBoxes.add((JCheckBox) component);
            }
        }

        if (checkBoxes.get(0).isSelected()) {
            setImplSelected();
        }
        if (checkBoxes.get(1).isSelected()) {
            setExtendSelected();
        }

        if (implButton && extButton) {
            String implText = textFields.get(0).getText();
            String extText = textFields.get(1).getText();

            if (implText.isBlank()) {
                System.out.println("impl list is blank");
                throw new IllegalStateException("Implementation Text is blank");
            }
            if (implText.isBlank()) {
                System.out.println("ext list is blank");
                throw new IllegalStateException("Extension Text is blank");
            }

            List<String> implString = fieldToListConverter(implText);
            List<String> extString = Collections.singletonList(fieldToListConverter(extText).get(0));

            result.add(implString);
            result.add(extString);

            return result;
        } else if (implButton) {
            String implText = textFields.get(0).getText();

            if (implText.isBlank()) {
                System.out.println("impl list is blank");
                throw new IllegalStateException("Implementation Text is blank");
            }

            List<String> implString = fieldToListConverter(implText);

            result.add(implString);

            return result;
        } else if (extButton) {
            String extText = textFields.get(1).getText();

            if (extText.isBlank()) {
                System.out.println("ext list is blank");
                throw new IllegalStateException("Implementation Text is blank");
            }

            List<String> extList = fieldToListConverter(extText);

            result.add(extList);

            return result;
        } else {
            throw new IllegalStateException("text fields are empty");
        }
    }

    private List<String> fieldToListConverter(String field) {
        return Arrays.asList(field.replaceAll(" ", "").split(","));
    }
}
