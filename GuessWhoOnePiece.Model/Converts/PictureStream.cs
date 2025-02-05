using System.IO;
using System.Reflection;

namespace GuessWhoOnePiece.Model.Converts
{
    public static class PictureStream
    {
        public static Stream? GetImageStream(string pictureName)
        {
            Assembly assembly = Assembly.GetExecutingAssembly();
            var resourceName = $"{assembly.GetName().Name}.Resources.Images.{pictureName}";
            return assembly.GetManifestResourceStream(resourceName);
        }
    }
}
