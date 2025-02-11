using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Model.Characters
{
    public class InfoCharacter
    {

        public InfoCharacter(string name, string picture)
        {
            Name = name;
            Picture = picture;
        }

        public InfoCharacter() { }

        public string Name { get; set; }
        public string Picture { get; set; }
    }
}
