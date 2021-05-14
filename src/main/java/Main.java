import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {

        Path source = Paths.get("//tmp//image.JPG");
        Path target = Paths.get("//tmp//image_to.JPG");

        // From image to byte[]
        BufferedImage bi = ImageIO.read(source.toFile());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        byte[] originByteArray = baos.toByteArray();
        System.out.println("Origin Byte Array: " + originByteArray);

        // From byte[] to base64
        byte[] encoded = Base64.getEncoder().encode(originByteArray);
        String stringBase64 = Base64.getEncoder().encodeToString(originByteArray);
        System.out.println("byte[] Base64 encoded: " + encoded);
        System.out.println("String Base64 encoded: " + stringBase64);

        // From base64 to byte[]
        byte[] destinyByteArray = Base64.getDecoder().decode(stringBase64);
        System.out.println("ByteArray count recheck: "+destinyByteArray.length);

        // FROM base64 string to image
        InputStream is = new ByteArrayInputStream(destinyByteArray);
        BufferedImage newBi = ImageIO.read(is);
        ImageIO.write(newBi, "jpg", target.toFile());
    }
}
