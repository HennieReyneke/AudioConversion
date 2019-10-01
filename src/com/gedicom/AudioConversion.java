package com.gedicom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hennie
 */
public class AudioConversion {

    protected final static Logger logger = LoggerFactory.getLogger(AudioConversion.class);

    public static void doFfmpeg(String inPath, String outPath) throws IOException, InterruptedException {
        String command = "c:\\ffmpeg.exe -i " + inPath + " -acodec pcm_alaw -ar 8000 -ab 8k -ac 1 -y " + outPath;
        Process pr = Runtime.getRuntime().exec(command);

        //Runtime rt = Runtime.getRuntime();
        //Process pr = rt.exec("cmd /c dir");
        //Process pr = rt.exec("c:\\ffmpeg.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            logger.info(line);
        }
        BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        String line2;
        while ((line2 = error.readLine()) != null) {
            logger.info(line2);
        }
        int exitVal = pr.waitFor();
        logger.info("Exited with error code '{}'", exitVal);
    }

    public static void wav2Flac(String inPath, String outPath) throws IOException, InterruptedException {
        String command = "c:\\ffmpeg.exe -i " + inPath + " -y " + outPath;
        Process pr = Runtime.getRuntime().exec(command);

        //Runtime rt = Runtime.getRuntime();
        //Process pr = rt.exec("cmd /c dir");
        //Process pr = rt.exec("c:\\ffmpeg.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            logger.info(line);
        }
        BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        String line2;
        while ((line2 = error.readLine()) != null) {
            logger.info(line2);
        }
        int exitVal = pr.waitFor();
        logger.info("Exited with error code '{}'", exitVal);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\3gp\\sample.3gp", "C:\\Users\\Hennie\\Desktop\\Conversion\\3gp\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\aac\\AAC_8khz_Mono_5.aac", "C:\\Users\\Hennie\\Desktop\\Conversion\\aac\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\aiff\\mono_8b_8kHz.aiff", "C:\\Users\\Hennie\\Desktop\\Conversion\\aiff\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\au\\yes.au", "C:\\Users\\Hennie\\Desktop\\Conversion\\au\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\flac\\BonkEnc_test14_level8_13s_VBR_471kbps_Mono_44100Hz_16bit.flac", "C:\\Users\\Hennie\\Desktop\\Conversion\\flac\\out.wav");
////            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\flac\\BIS1447-002-flac_16.flac", "C:\\Users\\Hennie\\Desktop\\Conversion\\flac\\out.wav");
////            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\m4a\\01auphonic-demo-unprocessed.m4a", "C:\\Users\\Hennie\\Desktop\\Conversion\\m4a\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\m4a\\auphonic-dehum-unprocessed.m4a", "C:\\Users\\Hennie\\Desktop\\Conversion\\m4a\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\ogg\\a2002011001-e02-128kbps.ogg", "C:\\Users\\Hennie\\Desktop\\Conversion\\ogg\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\wma\\baroqueloop90z_32kbit-sec_44.1kHz_mono.wma", "C:\\Users\\Hennie\\Desktop\\Conversion\\wma\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\wav\\1kHz_44100Hz_16bit_05sec.wav", "C:\\Users\\Hennie\\Desktop\\Conversion\\wav\\out.wav");
//            doFfmpeg("C:\\Users\\Hennie\\Desktop\\Conversion\\mp3\\1kHz_44100Hz_16bit_05sec.mp3", "C:\\Users\\Hennie\\Desktop\\Conversion\\mp3\\out.wav");
//            wav2Flac("C:\\Users\\Hennie\\Desktop\\Conversion\\wav\\SVI1PC_3_DT20070125182128_ID768817_RN2.WAV", "C:\\Users\\Hennie\\Desktop\\Conversion\\wav\\out.flac");
        } catch (IOException ex) {
            logger.error("IOException: '{}'", ex);
        } catch (InterruptedException ex) {
            logger.error("InterruptedException: '{}'", ex);
        } catch (Exception ex) {
            logger.error("General Exception: '{}'", ex);
        }
    }
}
