package Controller.Bord_Controllers;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class SoundController implements Runnable {

    private static SoundController soundController;
    private String sound;
    private Thread t;
    private boolean playing = true;



    public void play(String soundPath)
    {
        System.out.println("play me");
        this.sound = soundPath;
        t = new Thread(this);
        t.start();

    }

    public void run()
    {
        String[] songs = {"n.wav", "i.wav", "tati.wav", "m.wav", "C.wav", "k.wav", "dat_boi.wav", "g.wav", "i_am.wav", "s.wav"};
        while(true) {
            System.out.println("start");
            int random = (int) (Math.random() * songs.length);
            System.out.println(songs[random]);
            playSound(songs[random]);
            playing = true;
        }

        //playSound(sound);
        //playing = true;
    }

    private void playSound(String fileName)
    {
        File soundFile = new File(ClassLoader.getSystemClassLoader().getResource("Sound/" + fileName).getFile());
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
                System.out.println(nBytesRead);
            }
            System.out.println(nBytesRead);
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

