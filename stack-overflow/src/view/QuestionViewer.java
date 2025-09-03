package view;

import elements.Question;

public class QuestionViewer {
    public void view(Question question) {
        if (question == null) return;
        // TODO: Implement question, answer, comment view -> Composite pattern, each StackOverFlowElement will have a render method.
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Displaying question: " + question);
        question.print(0);
        System.out.println("================================================================================");
    }
}