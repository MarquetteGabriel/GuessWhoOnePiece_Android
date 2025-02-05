using System.IO;
using System.Threading.Tasks;

namespace GuessWhoOnePiece.Model.CsvManager
{
    public interface IFileServiceReader
    {
        Task<Stream> ReadAllLinesAsync(string path);

        string GetCsvPath();

        string GetPicturePath();
    }
}
