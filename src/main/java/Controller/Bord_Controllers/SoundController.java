package Controller.Bord_Controllers;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class SoundController implements Runnable {

    private static SoundController soundController;
    private String sound;
    private Thread t;
    private boolean playing = true;



    public void play(String soundPath)
    {
        this.sound = soundPath;
        t = new Thread(this);
        t.start();

    }

    public void run()
    {
        /*
        String[] songs = {"nTest.wav", "iTest.wav", "tatiTest.wav", "mTest.wav", "CTest.wav", "kTest.wav", "dat_boiTest.wav",  "i_amTest.wav", "sTest.wav"};
        while(true) {
            int random = (int) (Math.random() * songs.length);
//            System.out.println(songs[random]);
            playSound(songs[random]);
            playing = true;
        }
        */
        //playSound(sound);
        //playing = true;
        */
    }

    private void playSound(String fileName)
    {
        File soundFile = null;
        try {
            soundFile = new File(getClass().getResource("/Sound/" + fileName).toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        AudioInputStream audioInputStream = null;

        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        AudioFormat audioFormat = audioInputStream.getFormat();
        SourceDataLine line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try
        {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        line.start();
        int nBytesRead = 0;
        byte[] abData = new byte[128000];
        while (nBytesRead > -1)
        {
            while (playing){
                try
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0)
                {
                    int nBytesWritten = line.write(abData, 0, nBytesRead);
                }
                if(nBytesRead < 0){
                    playing = false;
                }
//                System.out.println(nBytesRead);
            }
//            System.out.println(nBytesRead);
        }

        line.drain();
        line.close();
    }

    public void setPlayingFalse(){
        this.playing = false;
    }

    public void setPlayingTrue(){
        this.playing = true;
    }

    public boolean getPlaying(){
        return this.playing;
    }
}

