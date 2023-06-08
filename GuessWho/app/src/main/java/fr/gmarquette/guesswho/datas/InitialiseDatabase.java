package fr.gmarquette.guesswho.datas;

import java.util.ArrayList;
import java.util.List;

class InitialiseDatabase
{
    private final List<Characters> listCharacters = new ArrayList<>();

    List<Characters> getDatabaseValues()
    {
        this.MugiwaraCrew();
        this.ShanksCrew();
        this.TeachCrew();
        this.CrossGuild();
        this.NewgateCrew();
        this.BigMomCrew();
        this.KaidoCrew();

        this.Citizens();
        this.WorldGovernment();
        this.Navy();
        this.Revolutionary();
        return listCharacters;
    }

    /*
    Pirates
     */

    private void MugiwaraCrew()
    {
        Characters luffy = new Characters("Monkey D. Luffy", true, "3000000000", 1, "Pirate", true, 19, "Mugiwara's Crew");
        Characters zoro = new Characters("Roronoa Zoro", false, "1111000000", 3, "Pirate", true, 21, "Mugiwara's Crew");







        listCharacters.add(luffy);
        listCharacters.add(zoro);
    }

    private void ShanksCrew()
    {

    }

    private void TeachCrew()
    {

    }

    private void CrossGuild()
    {

    }

    private void NewgateCrew()
    {

    }

    private void BigMomCrew()
    {

    }

    private void KaidoCrew()
    {

    }







    private void Citizens()
    {

    }

    private void WorldGovernment()
    {

    }

    private void Navy()
    {

    }

    private void Revolutionary()
    {

    }

    /*
    Other
     */

    void ClearList()
    {
        this.listCharacters.clear();
    }

}
