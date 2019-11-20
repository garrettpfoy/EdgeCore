package me.picklez.edgecore.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.picklez.edgecore.EdgeCore.Main;

public class ChatUtils {

	public static void write(String path, String playerName, String message) {
		final File file = new File(Main.getInstance().getDataFolder(), path);

		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm: dd.MM.YYYY");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

			bw.write("[" + sdf.format(new Date()) + "] " + "<" + playerName + "> " + message);
			bw.write(System.lineSeparator());

		}
		catch (final IOException ex) {
			ex.printStackTrace();
		}
	}



}
