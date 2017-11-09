package DatabaseConnector;

import com.pythagorithm.mathsmartv2.Assignment;
import com.pythagorithm.mathsmartv2.Question;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class DatabaseConnector {
    private String ID;
    public DatabaseConnector(String userID){
        this.ID=userID;
    }
    public DatabaseConnector(){}
    //=========================================================================================================================
    //OTHER
    //=========================================================================================================================
    public String login(String username){
        return "";
    }
    public String getSectionID(String studentID){
        return "";
    }
    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public ArrayList<Question> getAvailableQuestions(String topic, String sectionID){
        return new ArrayList<>();
    }
    public Question getQuestion(ArrayList<String> completedQuestion,int weight, String topic){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public Question getQuestion(String qID){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public String addQuestion(Question q){
        return "null";
    }
    public void updateQuestion(Question q){

    }
    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public ArrayList<Assignment> getAvailableAssignments(String sectionID){
        return new ArrayList<>();
    }
    public void getAssignmentProgress(String studentID,String aID,ArrayList<String> completedQuestions,double assignmentScore,int min){
        //Change values of completedQuestions, assignmentScore, and min
        //If not available, change value of completedQuestions to 'null'
    }
    public String addAssignment(String sectionList[],ArrayList<Assignment> assignmentList){
        return "JI";
    }
    public boolean saveAssignment(String studentID,String aID,ArrayList<String> completedQuestions,double assignmentScore,double overallScore){
        return true;
    }
    //=========================================================================================================================
    //SCORES
    //=========================================================================================================================
    public void updateScore(String aID,String qID,double questionScore,double overallScore){
    }
    public double getAssignmentScore(String aID,String studentID){
        return 0;
    }
    public double getOverallScore(String studentID){
        return 0;
    }
}
