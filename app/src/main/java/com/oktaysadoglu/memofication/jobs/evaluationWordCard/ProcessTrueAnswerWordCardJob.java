package com.oktaysadoglu.memofication.jobs.evaluationWordCard;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.db.UserWords;
import com.oktaysadoglu.memofication.db.UserWordsDao;

/**
 * Created by oktaysadoglu on 12/05/16.
 */
public class ProcessTrueAnswerWordCardJob extends EvaluationWordCardJob{

    public ProcessTrueAnswerWordCardJob(long mainWordId) {
        super(mainWordId);
    }

    @Override
    public void onRun() throws Throwable {

        UserWordsDao userWordsDao = Memofication.getUserWordsDao();

        UserWords userWords = userWordsDao.loadByRowId(getMainWordId());

        userWords.setWord(Memofication.getWordDao().load(getMainWordId()));

        if (userWords.getCount() == -1)
            userWords.setCount(0);
        else if (userWords.getCount() == 0)
            userWords.setCount(0);
        else
            userWords.setCount(userWords.getCount()-1);

        userWords.update();

    }
}
