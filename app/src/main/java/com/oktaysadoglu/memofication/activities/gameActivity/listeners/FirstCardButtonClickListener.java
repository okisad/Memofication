package com.oktaysadoglu.memofication.activities.gameActivity.listeners;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.model.WordCard;

import java.util.List;

/**
 * Created by oktaysadoglu on 11/05/16.
 */
public class FirstCardButtonClickListener extends CardButtonClickListener{

    private Button button;

    private Long mainId;

    private Long wordId;

    public FirstCardButtonClickListener(Context context, ViewGroup parent, SwipeDeck cardStack, List<Button> buttonsBundle, WordCard wordCard) {
        super(context,parent,cardStack,buttonsBundle,wordCard);

        setButton(buttonsBundle.get(0));

    }

    @Override
    public void onClick(View v) {

        Log.e("my",getWordCard().toString());

        setMainId(getWordCard().getMainWord().getId());

        setWordId(getWordCard().getWords().get(0).getId());

        Handler handler = new Handler();

        if (getMainId().equals(getWordId()))
            trueAnswerProcess(handler,getMainId(),getButton());
        else
            falseAnswerProcess(handler,getMainId(),getButton());


    }


    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}
