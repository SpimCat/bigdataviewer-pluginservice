package de.mpicbg.rhaase.bdvtools.plugins.sourcelistmanager;

import bdv.BigDataViewer;
import de.mpicbg.rhaase.bdvtools.BDVUtilities;
import de.mpicbg.rhaase.bdvtools.BigDataViewerPlugin;
import de.mpicbg.rhaase.bdvtools.SupportsBigDataViewerToolBarButton;
import de.mpicbg.rhaase.utilities.ImageJUtilities;
import org.scijava.plugin.Plugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * This plugin opens a window showing all current sources in the BigDataViewer. It allows the user to select source, delete them, outline them, zoom to them...
 * 
 * 
 * @author Robert Haase, Scientific Computing Facility, MPI-CBG, rhaase@mpi-cbg.de
 * @version 1.0.0 Nov 11, 2015
 */
@Plugin(type = BigDataViewerPlugin.class)
public class SourceManagerPlugin implements BigDataViewerPlugin,
																						SupportsBigDataViewerToolBarButton
{
	BDVSourceListManager bdvrc = null;
	JButton toolButton = null;


	BigDataViewer bdv = null;
	@Override
	public void setBdv(BigDataViewer bdv)
	{
		this.bdv = bdv;
	}

	@Override public void run()
	{

	}

	@Override public
	int addToolBarButtons(int verticalPosition) {

		ActionListener al = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleSourceManagerVisibility();
			}
		};
		
		if (verticalPosition > -1)
		{
			// ------------------------------------------------
			// Add a button to the BigDataViewer
			final JPanel contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.setBounds(0, verticalPosition, 22, 22);
			bdv.getViewer().getDisplay().add(contentPanel);



			toolButton = new JButton();
			toolButton.setBounds(0, 0, 22, 22);
			toolButton.setToolTipText("Show/Hide source manager");
			toolButton.setIcon(new ImageIcon(ImageJUtilities.getImageFromString(
							// 0123456789abcdef
							/* 0 */"                " +
							/* 1 */"                " +
							/* 2 */"################" +
							/* 3 */"#              #" +
							/* 4 */"# # #          #" +
							/* c */"#  #    ####   #" +
							/* c */"# # #          #" +
							/* 5 */"#              #" +
							/* 5 */"#   #          #" +
							/* 7 */"# # #   #####  #" +
							/* 8 */"#  #           #" +
							/* 9 */"#              #" +
							/* a */"#              #" +
							/* b */"################" +
							/* d */"                " +
							/* f */"                ")));


			toolButton.addActionListener(al);

			contentPanel.add(toolButton);
		}
		

		BDVUtilities.addToBDVMenu(bdv, new String[]{ "Source"}, "Source manager", al);
		return verticalPosition + 22;
	}
	
	public void toggleSourceManagerVisibility()
	{
		if (bdvrc == null)
		{
			bdvrc = new BDVSourceListManager(bdv);
		}
		
		if (!bdvrc.isVisible())
		{
			bdvrc.setVisible(true);
			if (toolButton != null)
			{
				toolButton.setSelected(true);
			}
		}
		else
		{
			bdvrc.setVisible(false);
			if (toolButton != null)
			{
				toolButton.setSelected(false);
			}
		}
		//bdvrc.setBDV(bdv);
	}
}
