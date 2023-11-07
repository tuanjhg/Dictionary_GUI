package Game.Quiz.models;
import java.io.Serializable;
import java.util.ArrayList;
public class Category implements Serializable {
    private ArrayList<Question> _questions;
    private String _title;

    public Category(String title) {
        _title = title;
        _questions = new ArrayList<Question>();
    }
    public void add(Question q) {
        _questions.add(q);
    }
    public String getTitle() {
        return _title;
    }
    public Question get(int i) {
        return _questions.get(i);
    }
    public ArrayList<Question> getQuestions(){
        return _questions;
    }
    public int getQuestionCount() {
        return _questions.size();
    }
}
