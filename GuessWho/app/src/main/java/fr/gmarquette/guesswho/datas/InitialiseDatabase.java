package fr.gmarquette.guesswho.datas;

import java.util.ArrayList;
import java.util.List;

public class InitialiseDatabase
{
    private List<Characters> listCharacters = new ArrayList<>();

    public List<Characters> getDatabaseValues()
    {
        this.MugiwaraCrew();



        return listCharacters;
    }



    private void MugiwaraCrew()
    {
        Characters luffy = new Characters("Monkey D. Luffy", true, "3000000000", 1, "Pirate", true, 19, "Mugiwara's Crew");
        Characters zoro = new Characters("Roronoa Zoro", false, "1111000000", 3, "Pirate", true, 21, "Mugiwara's Crew");







        listCharacters.add(luffy);
        listCharacters.add(zoro);
    }

}
