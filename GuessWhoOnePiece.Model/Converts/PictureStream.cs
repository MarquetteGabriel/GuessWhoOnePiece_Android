using System.IO;
using System.Reflection;

namespace GuessWhoOnePiece.Model.Converts
{
    public static class PictureStream
    {
        public static Stream? GetImageStream(string pictueName)
        {
            Assembly assembly = Assembly.GetExecutingAssembly();
            var resourceName = $"{assembly.GetName().Name}.Resources.Images.{pictueName}";
            return assembly.GetManifestResourceStream(resourceName);
        }
    }
}
