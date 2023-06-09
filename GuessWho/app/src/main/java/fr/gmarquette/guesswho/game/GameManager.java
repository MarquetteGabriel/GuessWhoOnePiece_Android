package fr.gmarquette.guesswho.game;

import static java.lang.Integer.parseInt;

import fr.gmarquette.guesswho.datas.Characters;

public class GameManager
{
    public static boolean isAlive(Characters characters, Characters characterSearched)
    {
        return (characters.isAlive() == characterSearched.isAlive());
    }

    public static boolean hasEatenDevilFruit(Characters characters, Characters characterSearched)
    {
        return (characters.hasDevilFruit() == characterSearched.hasDevilFruit());
    }
    
    public static String whatBounty(Characters characters, Characters characterSearched)
    {
        String messageReturned;
        if(characters.getBounty().equals("Unkwon"))
        {
            if(characterSearched.getBounty().equals("Unknown"))
            {
                messageReturned = "Les 2 sont inconnus";
            }
            else
            {
                messageReturned = "La prime est connue";
            }
        }
        else if (characters.getBounty().equals("0"))
        {
            if(characterSearched.getBounty().equals("0"))
            {
                messageReturned = "ils sont pas recherches";
            }
            else
            {
                messageReturned = "Le personnage est recherché";
            }
        }
        else if (parseInt(characters.getBounty().substring(0, characters.getBounty().length() - 3)) ==
                parseInt(characterSearched.getBounty().substring(0, characterSearched.getBounty().length() - 3)))
        {
            messageReturned = "Même prime";
        }
        else
        {
            int diff = parseInt(characters.getBounty().substring(0, characters.getBounty().length() - 3)) -
                    parseInt(characterSearched.getBounty().substring(0, characterSearched.getBounty().length() - 3));
            if(diff > 0)
            {
                // prime plus basse pour le perso chercher
                messageReturned = getBountyFork(diff);
            }
            else
            {
                messageReturned = getBountyFork(-diff);
            }

        }
        return messageReturned;
    }

    public static boolean isSameName(Characters characters, Characters characterSearched)
    {
        return (characters.getName().equals(characterSearched.getName()));
    }

    public static String getAppearanceDiff(Characters characters, Characters characterSearched)
    {
        int diff = characters.getFirstAppearance() - characterSearched.getFirstAppearance();
        if(diff <= 50)
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
        if(characters.getType().equals("Navy") && characterSearched.equals("Navy"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if (characters.getType().equals("Pirate") && characterSearched.equals("Pirate"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if(characters.getType().equals("Revolutionary") && characterSearched.equals("Revolutionary"))
        {
            return "C'est bien un " + characters.getType();
        }
        else if (characters.getType().equals("Citizen") && characterSearched.equals("Citizen"))
        {
            return "C'est bien un " + characters.getType();
        }
        else
        {
            return "Il n'est pas de " + characters.getType();
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

    public static boolean getCrew(Characters characters, Characters characterSearched)
    {
        return characters.getCrew().equals(characterSearched.getCrew());
    }


    private static String getBountyFork(int diff)
    {
        if(diff <= 50)
        {
            return "-50M d'écart";
        } else if (diff <= 200) {
            return "Entre 50 et 200M d'écart";
        } else if(diff <= 500)
        {
            return "Entre 200 et 500M d'écart";
        }
        else {
            return "Plus de 500 d'écart";
        }
    }

}
