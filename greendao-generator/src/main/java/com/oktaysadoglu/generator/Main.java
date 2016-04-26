package com.oktaysadoglu.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1,"com.oktaysadoglu.memofication.db");

        Entity word = schema.addEntity("Word");

        word.addIdProperty();
        word.addStringProperty("word");
        word.addStringProperty("mean");
        word.addStringProperty("type");

        Entity userWords = schema.addEntity("UserWords");

        userWords.addIdProperty();
        Property userWordId = userWords.addLongProperty("word_id").getProperty();
        userWords.addIntProperty("count");
        userWords.addIntProperty("is_on_notification");
        userWords.addToOne(word, userWordId);

        Entity lastWordNumber = schema.addEntity("LastWordNumber");

        lastWordNumber.addIdProperty();
        lastWordNumber.addIntProperty("last_number");

        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema,"./app/src/main/java");

        /*List<Integer> integers = new ArrayList<>();

        Random random = new Random();

        int i = 0;

        integers.add(3);

        while (i < 3) {

            boolean bool = false;

            int randomValue = random.nextInt(4000)+1;

            for (Integer integer : integers) {

                if (integer == randomValue) {

                    break;

                }

                if (integers.get(integers.size() - 1) == integer) {

                    bool = true;

                }

            }

            if (bool) {

                integers.add(randomValue);

                i++;

            }


        }

        System.out.println(integers);*/


       /* int size = 0;

        int lowerBound = 40;

        int upperBound = 50;

        for (int i = 0; i < list.size() ; i++){

            int wordId = list.get(i);

            if(wordId % 50 > lowerBound && wordId % 50 <=upperBound){

                size++;

            }

        }

        if(upperBound == 50){

            size++;

        }

        System.out.println(size);*/

    }

    private void listControl(List<Integer> integers) {

        for (int v = 0; v < integers.size(); v++) {

            int s = integers.get(v);

            for (int k = v + 1; k < integers.size(); k++) {

                if (s == integers.get(k)) {

                    System.out.println("PROBLEM");

                }

            }

        }

    }


    private int calculateStartWordId(int level, int section) {

        int startLevelId = ((level * 50) - 50) + 1;

        if (section == 0) {

            return startLevelId;

        } else if (section == 1) {

            return startLevelId + 10;

        } else if (section == 2) {

            return startLevelId + 20;

        } else if (section == 3) {

            return startLevelId + 30;

        } else {

            return startLevelId + 40;

        }

    }

}
