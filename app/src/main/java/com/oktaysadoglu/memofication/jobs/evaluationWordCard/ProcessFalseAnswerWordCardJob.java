package com.oktaysadoglu.memofication.jobs.evaluationWordCard;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.UserWords;
import com.oktaysadoglu.memofication.db.UserWordsDao;

/**
 * Created by oktaysadoglu on 12/05/16.
 */
public class ProcessFalseAnswerWordCardJob extends EvaluationWordCardJob {
    public ProcessFalseAnswerWordCardJob(long mainWordId) {
        super(mainWordId);
    }

    @Override
    public void onRun() throws Throwable {

        UserWordsDao userWordsDao = Memofication.getUserWordsDao();

        UserWords userWords = userWordsDao.loadByRowId(getMainWordId());

        userWords.setWord(Memofication.getWordDao().load(getMainWordId()));

        userWords.setIs_on_notification(1);

        if (userWords.getCount() == -1)
            userWords.setCount(2);
        else if (userWords.getCount() == 0)
            userWords.setCount(2);
        else
            userWords.setCount(userWords.getCount()+1);

        userWords.update();

    }
}
