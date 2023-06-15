/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.guesswho.GameSystem;

import fr.gmarquette.guesswho.GameSystem.BountyManager.BountyFactory;
import fr.gmarquette.guesswho.GameData.Database.Characters;

public class GameManager
{


    private static final String BothAlive = "Les 2 sont en vies";
    private static final String BothDead = "Les 2 sont morts";
    private static final String WrongAlive = "Faux, il n'est pas en vie";
    private static final String WrongDead = "Faux, il n'est pas mort";

    private static final String BothFruit= "Les 2 sont ont mange des fruits";
    private static final String BothNoFruit = "Les 2 n'ont pas mangé de fruit";
    private static final String WrongFruit = "Faux, il n'est pas n'a pas mangé d efruit";
    private static final String WrongNoFruit = "Faux, il a mangé un fruit";

    private static final String GoodCrew = "Ils sont dans le meme équipage";

    private static final String WrongCrew = "Ils ne sont pas dnas le mm équipage";

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
            return "Bon chapitre";
        }
        else if(diff <= 50)
        {
            return "-50 chapitres d'écart";
        } else if (diff <= 200) {
            return "Entre 50 et 200 chapitres d'écart";
        } else if(diff <= 500)
        {
            return "Entre 200 et 500 chapitres d'écart";
        }
        else {
            return "Plus de 500 chapitres d'écart";
        }
    }

    public static String getType(Characters characters, Characters characterSearched)
    {
        if(characters.getType().equals("Navy") && characterSearched.getType().equals("Navy"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if (characters.getType().equals("Pirate") && characterSearched.getType().equals("Pirate"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if(characters.getType().equals("Revolutionary") && characterSearched.getType().equals("Revolutionary"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if (characters.getType().equals("Citizen") && characterSearched.getType().equals("Citizen"))
        {
            return "C'est bien un " + characters.getType();
        }
        else
        {
            return "Il n'est pas un " + characters.getType();
        }
    }

    public static String getAge(Characters characters, Characters characterSearched)
    {
        int diff = Math.abs(characters.getAge() - characterSearched.getAge());
        if(diff <= 10)
        {
            return "Moins de 10 ans d'écart";
        }
        else if(diff <= 35)
        {
            return "Entre 10 et 35 ans d'écart";
        }
        else
        {
            return "Plus de 35 ans d'écart";
        }
    }

    public static String getCrew(Characters characters, Characters characterSearched)
    {
        return characters.getCrew().equals(characterSearched.getCrew()) ? GoodCrew : WrongCrew;
    }


}
