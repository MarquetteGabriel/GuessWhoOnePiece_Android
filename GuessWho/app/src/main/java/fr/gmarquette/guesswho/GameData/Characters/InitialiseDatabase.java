/*
 *
 * @brief Copyright (c) 2023 Gabriel Marquette
 *
 * Copyright (c) 2023 Gabriel Marquette. Tous droits réservés.
 *
 */

package fr.gmarquette.guesswho.GameData.Characters;

import java.util.ArrayList;
import java.util.List;

import fr.gmarquette.guesswho.GameData.Database.Characters;

public class InitialiseDatabase
{
    private final List<Characters> listCharacters = new ArrayList<>();

    public List<Characters> getDatabaseValues()
    {
        this.MugiwaraCrew();
        this.ShanksCrew();
        this.TeachCrew();
        this.CrossGuild();
        this.NewgateCrew();
        this.BigMomCrew();
        this.KaidoCrew();

        this.EnerCrew();

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
        Characters nami = new Characters("Nami", false, "366000000", 8, "Pirate", true, 20, "Mugiwara's Crew");
        Characters usopp = new Characters("Usopp", false, "500000000", 23, "Pirate", true, 19, "Mugiwara's Crew");
        Characters sanji = new Characters("Vinsmoke Sanji", false, "1032000000", 43, "Pirate", true, 21, "Mugiwara's Crew");
        Characters chopper = new Characters("Tony-Tony Chopper", true, "1000", 134, "Pirate", true, 17, "Mugiwara's Crew");
        Characters robin = new Characters("Nico Robin", true, "930000000", 114, "Pirate", true, 30, "Mugiwara's Crew");
        Characters franky = new Characters("Franky / Cutty Flam", false, "394000000", 329, "Pirate", true, 36, "Mugiwara's Crew");
        Characters brook = new Characters("Brook", true, "383000000", 442, "Pirate", true, 90, "Mugiwara's Crew");
        Characters jinbei = new Characters("Jinbei", false, "1100000000", 528, "Pirate", true, 46, "Mugiwara's Crew");

        listCharacters.add(luffy);
        listCharacters.add(zoro);
        listCharacters.add(nami);
        listCharacters.add(usopp);
        listCharacters.add(sanji);
        listCharacters.add(chopper);
        listCharacters.add(robin);
        listCharacters.add(franky);
        listCharacters.add(brook);
        listCharacters.add(jinbei);
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
        Characters ace = new Characters("Portgas D. Ace", true, "550000000", 154, "Pirate", false, 20, "Newgate's Crew");

        listCharacters.add(ace);
    }

    private void BigMomCrew()
    {
        Characters katakuri = new Characters("Charlotte Katakuri", true, "1057000000", 860, "Pirate", true, 48, "BigMom's Crew");

        listCharacters.add(katakuri);
    }

    private void KaidoCrew()
    {

    }

    private void EnerCrew()
    {
        Characters ener = new Characters("Ener", true, "Unknown", 254, "Pirate", true, 39, "Ener's Crew");

        listCharacters.add(ener);
    }







    private void Citizens()
    {

    }

    private void WorldGovernment()
    {

    }

    private void Navy()
    {
        Characters kizaru = new Characters("Borsalino / Kizaru", true, "0", 397, "Navy", true, 55, "Navy's Crew");

        listCharacters.add(kizaru);
    }

    private void Revolutionary()
    {
        Characters sabo = new Characters("Sabo", true, "602000000", 583, "Revolutionary", true, 22, "Revolutionary's Crew");

        listCharacters.add(sabo);
    }

    /*
    Other
     */

    public void ClearList()
    {
        this.listCharacters.clear();
    }

}
