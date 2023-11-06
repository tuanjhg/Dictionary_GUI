package Game.models;
import Game.ObjectDB;
import Game.utils.FileHandler;
import Game.utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizModel {
    private int _winning;
    private int _remainingQuestion;
    private ArrayList<Category> _cats;
    private Question _activeQuestion;
    public QuizModel() {
        _winning = 0;
        _cats = FileHandler.loadCategory();
        updateRemainingQuestion();
        try {
            if (FileHandler.saveFileExist()) {
                load();
                System.out.println("load file suscessfuly load");
            }
        } catch (Exception e) {
            System.out.println("Loading failed. Maybe trying removing user.save in the working directory");
            e.printStackTrace();
        }
    }
    public boolean answerQuestion(Question question, String input) {
        question.setAttempted(true);
        _remainingQuestion -= 1;
        if (question.getAnswer().equalsIgnoreCase(input)) {
            _winning += question.getScore();
            return true;
        } else {
            _winning -= question.getScore();
            return false;
        }

    }
    public void updateRemainingQuestion() {
        _remainingQuestion = 0;
        for (Category cat : _cats) {
            for (Question question : cat.getQuestions()) {
                if (!question.isAttempted()) {
                    _remainingQuestion += 1;
                }
            }
        }
    }
    public void save() {
        ObjectDB db = new ObjectDB();
        db.setWinning(_winning);
        HashMap<String, Boolean> isAttemptedMap = new HashMap<String, Boolean>();
        for (Category cat : _cats) {
            for (Question question : cat.getQuestions()) {
                isAttemptedMap.put(question.toString(), question.isAttempted());
            }
        }
        db.setIsAttemptedMap(isAttemptedMap);
        FileHandler.saveDB(db);
    }

    public void load() {
        ObjectDB db = FileHandler.loadDB();
        _winning = db.getWinning();
        Map<String, Boolean> isAttemptedMap = db.getIsAttemptedMap();
        for (Category cat : _cats) {
            for (Question question : cat.getQuestions()) {
                if (isAttemptedMap.get(question.toString()) == true) {
                    question.setAttempted(true);
                }
            }

        }
        updateRemainingQuestion();
    }
    public void reset() {
        _winning = 0;
        _remainingQuestion = 0;
        for (Category cat : _cats) {
            for (Question question : cat.getQuestions()) {
                question.setAttempted(false);
                _remainingQuestion += 1;
            }
        }
    }

    public void setActiveQuestion(Question question) {
        _activeQuestion = question;
    }
    public Question getActiveQuestion() {
        return _activeQuestion;
    }
    public ArrayList<Category> getCategoryList() {
        return _cats;
    }
    public int getWinning() {
        return _winning;
    }
    public String getWinningStr() {
        return "$" + Integer.toString(_winning);
    }
    public int getRemainingQuestionCount() {
        return _remainingQuestion;
    }

}
