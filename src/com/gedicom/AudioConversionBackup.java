package com.gedicom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hennie
 */
public class AudioConversionBackup {

    protected final static Logger logger = LoggerFactory.getLogger(AudioConversionBackup.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AudioConversionBackup ac = new AudioConversionBackup();
            AudioFormat pcmformat = new AudioFormat(8000, 16, 1, true, false);
            File file = new File("C:\\Users\\Hennie\\Desktop\\Conversion\\Media-Convert_test2_PCM_Mono_VBR_8SS_48000Hz.wav");
            try (AudioInputStream inFileAIS = AudioSystem.getAudioInputStream(file);) {
                CompressInputStream cis = new CompressInputStream(inFileAIS, true);
            }
        } catch (UnsupportedAudioFileException ex) {
            java.util.logging.Logger.getLogger(AudioConversionBackup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AudioConversionBackup.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ac.ConvertFileToAIFF("C:\\Users\\Hennie\\Desktop\\Conversion\\violin00.aif", "C:\\Users\\Hennie\\Desktop\\Conversion\\violin00out.aif");
        //ac.convertWavToALaw("C:\\Users\\Hennie\\Desktop\\Conversion\\msg.wav");
    }

    public void ConvertFileToAIFF(String inputPath,
            String outputPath) {
        AudioFileFormat inFileFormat;
        File inFile;
        File outFile;
        try {
            inFile = new File(inputPath);
            outFile = new File(outputPath);
        } catch (NullPointerException ex) {
            logger.error("Error: one of the ConvertFileToAIFF parameters is null!");
            return;
        }
        try {
            // query file type
            inFileFormat = AudioSystem.getAudioFileFormat(inFile);
            if (inFileFormat.getType() != AudioFileFormat.Type.AIFF) {
                // inFile is not AIFF, so let's try to convert it.
                AudioInputStream inFileAIS = AudioSystem.getAudioInputStream(inFile);
                inFileAIS.reset(); // rewind
                if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF, inFileAIS)) {
                    // inFileAIS can be converted to AIFF. 
                    // so write the AudioInputStream to the
                    // output file.
                    AudioSystem.write(inFileAIS, AudioFileFormat.Type.AIFF, outFile);
                    System.out.println("Successfully made AIFF file, "
                            + outFile.getPath() + ", from "
                            + inFileFormat.getType() + " file, "
                            + inFile.getPath() + ".");
                    inFileAIS.close();
                    return; // All done now
                } else {
                    System.out.println("Warning: AIFF conversion of "
                            + inFile.getPath()
                            + " is not currently supported by AudioSystem.");
                }
            } else {
                System.out.println("Input file " + inFile.getPath()
                        + " is AIFF." + " Conversion is unnecessary.");
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: " + inFile.getPath()
                    + " is not a supported audio file type!");
            return;
        } catch (IOException e) {
            System.out.println("Error: failure attempting to read "
                    + inFile.getPath() + "!");
            return;
        }
    }

    public void convertULawToWav(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }
        try {
            long fileSize = file.length();
            int frameSize = 160;
            long numFrames = fileSize / frameSize;
            AudioFormat audioFormat = new AudioFormat(Encoding.ULAW, 8000, 8, 1, frameSize, 50, true);
            AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(file), audioFormat, numFrames);
            AudioSystem.write(audioInputStream, Type.WAVE, new File("C:\\file.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void convertWavToALaw(String filename) {
     File file = new File(filename);
     if (!file.exists()) {
     return;
     }
     try {
     long fileSize = file.length();
     int frameSize = 160;
     long numFrames = fileSize / frameSize;
     AudioFormat audioFormat = new AudioFormat(Encoding.ALAW, 8000, 8, 1, frameSize, 50, true);
     AudioInputStream audioInputStream = new AudioInputStream(new FileInputStream(file), audioFormat, numFrames);
     AudioSystem.write(audioInputStream, Type.WAVE, new File("C:\\Users\\Hennie\\Desktop\\Conversion\\ALaw.wav"));
     } catch (IOException e) {
     e.printStackTrace();
     }
     }
    
     File soundFile;
     AudioInputStream stream;
     AudioFormat format;
     
     stream=AudioSystem.getAudioInputStream(soundFile);
     format=stream.getFormat();
               
     if(format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
     {
     stream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,stream);
     format = stream.getFormat();
     }
    
     //Snippet
     File soundFile;
     AudioInputStream stream;
     AudioFormat format;
     
     stream=AudioSystem.getAudioInputStream(soundFile);
     format=stream.getFormat();
               
     if(format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
     {
     stream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,stream);
     format = stream.getFormat();
     }
     */
}
