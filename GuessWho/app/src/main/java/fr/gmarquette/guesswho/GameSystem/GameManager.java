/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem;

import fr.gmarquette.guesswho.GameData.Database.Characters;
import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyFactory;

public class GameManager
{


    private static final String BothAlive = "Both are alive";
    private static final String BothDead = "Both are dead";
    private static final String WrongAlive = "He is not alive";
    private static final String WrongDead = "He is not dead yet";

    private static final String BothFruit= "Both have eaten devil fruit";
    private static final String BothNoFruit = "Both have not eaten devil fruit";
    private static final String WrongFruit = "He has not eaten devil fruit";
    private static final String WrongNoFruit = "He has eaten devil fruit";

    private static final String GoodCrew = "Same Crew";

    private static final String WrongCrew = "Wrong Crew";

    public static String isAlive(Characters characters, Characters characterSearched)
    {
        return characters.isAlive() && characterSearched.isAlive() ? BothAlive :
            !characters.isAlive() && !characterSearched.isAlive() ? BothDead :
                characters.isAlive() && !characterSearched.isAlive() ? WrongAlive : WrongDead;
    }

    public static String hasEatenDevilFruit(Characters characters, Characters characterSearched)
    {
        return characters.hasDevilFruit() && characterSearched.hasDevilFruit() ? BothFruit :
                !characters.hasDevilFruit() && !characterSearched.hasDevilFruit() ? BothNoFruit :
                        characters.hasDevilFruit() && !characterSearched.hasDevilFruit() ? WrongFruit : WrongNoFruit;
    }

    public static String lookingForBounty(Characters characters, Characters characterSearched)
    {
        return BountyFactory.whatBounty(characters, characterSearched);
    }




    public static boolean isSameName(Characters characters, Characters characterSearched)
    {
        return (characters.getName().equals(characterSearched.getName()));
    }

    public static String getAppearanceDiff(Characters characters, Characters characterSearched)
    {
        int diff = characters.getFirstAppearance() - characterSearched.getFirstAppearance();
        if(diff == 0)
        {
            return "Same Chapter";
        }
        else if(diff <= 50)
        {
            return " Less than 50 Chapter gap";
        } else if (diff <= 200) {
            return "Between 50 and 200 Chapter gap";
        } else if(diff <= 500)
        {
            return "Between 200 and 500 Chapter gap";
        }
        else {
            return "More than 500 Chapter gap";
        }
    }

    public static String getType(Characters characters, Characters characterSearched)
    {
        if(characters.getType().equals("Navy") && characterSearched.getType().equals("Navy"))
        {
            return "He is a " + characters.getType();
        }
        else if (characters.getType().equals("Pirate") && characterSearched.getType().equals("Pirate"))
        {
            return "He is a " + characters.getType();
        }
        else if(characters.getType().equals("Revolutionary") && characterSearched.getType().equals("Revolutionary"))
        {
            return "He is a " + characters.getType();
        }
        else if (characters.getType().equals("Citizen") && characterSearched.getType().equals("Citizen"))
        {
            return "He is a " + characters.getType();
        }
        else
        {
            return "He is not a " + characters.getType();
        }
    }

    public static String getAge(Characters characters, Characters characterSearched)
    {
        int diff = Math.abs(characters.getAge() - characterSearched.getAge());
        if(diff <= 10)
        {
            return "Less than 10 years gap";
        }
        else if(diff <= 35)
        {
            return "Between 10 and 35 years gap";
        }
        else
        {
            return "More than 35 years gap";
        }
    }

    public static String getCrew(Characters characters, Characters characterSearched)
    {
        return characters.getCrew().equals(characterSearched.getCrew()) ? GoodCrew : WrongCrew;
    }


}
