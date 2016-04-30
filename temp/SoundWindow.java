package pl.siek.tester;

//NOT OPERATIONAL

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SoundWindow extends javax.swing.JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5249072959225280396L;
	private AudioInputStream audioInputStream;
	private AudioFormat audioFormat;
	private SourceDataLine line;
		
	boolean getFromURL = false;
	boolean	bBigEndian = false;		
	int	nSampleSizeInBits = 16;
	String	strMixerName = null;
	int	nExternalBufferSize = 128000;
	int	nInternalBufferSize = AudioSystem.NOT_SPECIFIED;
	
	private JPanel panel;
	private JButton play;
	private JButton stop;
	
	public SoundWindow(String path){
		initiateComponents(null,null, null);	
		init(path);
	}	
	public SoundWindow(String path,String soundTitle,String errorTitle,String errorMsg){
		initiateComponents(soundTitle,errorTitle,errorMsg);
		init(path);
	}	

	private void initiateComponents(String soundTitle, String errorTitle,String errorMsg){
		panel=new JPanel();		
		play=new JButton("|>");
		stop=new JButton("[]");
		
		panel.add(play);
		panel.add(stop);
		
		play.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt){play(evt);}});
		
		if(soundTitle!=null)this.setTitle(soundTitle);
		else this.setTitle("Sound");
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SoundWindow("g:\\plik.wav","Picchor","Eat brains!","Your brain is the size of a peanut. Go! See!").setVisible(true);
            }
        });

	}
	
	private void init(String path){		
		try {
			if (getFromURL){			
				URL input = new URL(path);
				audioInputStream = AudioSystem.getAudioInputStream(input);			
			}
			else{				
				File input = new File(path);
				audioInputStream = AudioSystem.getAudioInputStream(input);
			}		
		}catch(MalformedURLException e){
			//TODO HANDLE!!
			System.out.println(e);
		}catch(UnsupportedAudioFileException e){
			//TODO HANDLE!!
			System.out.println(e);
		}catch(IOException e){
			//TODO HANDLE!!
			System.out.println(e);
		}
		if(audioInputStream==null)System.out.println("wuz here");
		audioFormat = audioInputStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat, nInternalBufferSize);
		
		if (AudioSystem.isLineSupported(info)){
			AudioFormat sourceFormat = audioFormat;
			AudioFormat targetFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				sourceFormat.getSampleRate(),
				nSampleSizeInBits,
				sourceFormat.getChannels(),
				sourceFormat.getChannels() * (nSampleSizeInBits / 8),
				sourceFormat.getSampleRate(),
				bBigEndian);			
			audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
			audioFormat = audioInputStream.getFormat();			
		}

		line = getSourceDataLine(strMixerName, audioFormat, nInternalBufferSize);
	}
	
	private void play(java.awt.event.ActionEvent evt){
		line.start();
		int	nBytesRead = 0;
		byte[]	abData = new byte[nExternalBufferSize];
		while (nBytesRead != -1)
		{
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
				int	nBytesWritten = line.write(abData, 0, nBytesRead);				
			}
		}
	
		line.drain();
		line.close();
	}
	private static SourceDataLine getSourceDataLine(String strMixerName,AudioFormat audioFormat,int nBufferSize) {
		SourceDataLine line = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat, nBufferSize);
		try{
			/*if (strMixerName != null){
				Mixer.Info mixerInfo = AudioCommon.getMixerInfo(strMixerName);
				if (mixerInfo == null){
					//TODO HANDLE EXCEPTION
				}
				Mixer mixer = AudioSystem.getMixer(mixerInfo);
				line = (SourceDataLine) mixer.getLine(info);
			}
			else*/			
				line = (SourceDataLine) AudioSystem.getLine(info);
			
			line.open(audioFormat, nBufferSize);
		}
		catch (LineUnavailableException e)
		{
			//TODO HANDLE
			System.out.println("getSourceDataLine line unavailable exception");
		}
		catch (Exception e)
		{
			//TODO HANDLE
			System.out.println("getSourceDataLine unknown exception");
		}
		return line;		
	}
	
}

