namespace GuessWhoOnePiece.Model.CsvManager
{
    public interface IFileServiceReader
    {
        string GetCsvPath { get; }

        string GetPicturePath { get; }
    }
}
