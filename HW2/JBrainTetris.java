package tetris;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JBrainTetris extends JTetris {

	JCheckBox brainMode;
	Brain brain;
	// boolean seen = false;
	Brain.Move best;
	private int currCount;
	private JCheckBox animate;
	private JSlider adv;
	private JPanel little;
	private JLabel advLab;

	JBrainTetris(int pixels) {
		super(pixels);

		best = new Brain.Move();
		brain = new DefaultBrain();
		currCount = super.count;
	}

	/**
	 * added: brain mode check box, adversary slider and animate check box for
	 * drops while using brain.
	 */
	@Override
	public JComponent createControlPanel() {
		JPanel panel = (JPanel) super.createControlPanel();

		panel.add(new JLabel("Brain:"));
		brainMode = new JCheckBox("Brain active");
		panel.add(brainMode);
		brainMode.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (brainMode.isSelected())
					brainMode.setText("Brain active");
				else
					brainMode.setText("Brain passive");
			}
		});

		animate = new JCheckBox("Animate Falling");
		animate.setSelected(true);
		panel.add(animate);

		little = new JPanel();
		advLab = new JLabel("OK");
		little.add(new JLabel("Adversary:"));
		adv = new JSlider(0, 100, 0);
		adv.setPreferredSize(new Dimension(100, 15));
		little.add(adv);
		panel.add(little);
		panel.add(advLab);

		return panel;

	}

	/**
	 * added logic for brain mode. when brain mode is active every tick down
	 * rotates, moves and/or drops piece if possible.
	 */
	@Override
	public void tick(int verb) {
		/// *

		if (brainMode.isSelected() && verb == DOWN) {
			if (count != currCount) {
				board.undo();
				currCount = count;
				best = brain.bestMove(board, currentPiece, HEIGHT, best);
			}
			if (count == currCount && best != null) {

				if (best.x < currentX)
					super.tick(LEFT);
				if (best.x > currentX)
					super.tick(RIGHT);
				if (!currentPiece.equals(best.piece) && currentY <= HEIGHT)
					super.tick(ROTATE);
				if (!animate.isSelected() && best.x == currentX && currentY > best.y && currentPiece.equals(best.piece))
					super.tick(DROP);
			}
		}
		super.tick(verb);
	}

	/**
	 * decide between worst piece possible and random next piece by super class
	 */
	@Override
	public Piece pickNextPiece() {
		// seen = false;
		random = new Random();

		int ran = random.nextInt(101);
		if (ran >= adv.getValue()) {
			advLab.setText("OK");
			return super.pickNextPiece();
		} else {
			advLab.setText("*OK*");
			return getWorst();
		}

		// return super.pickNextPiece();
	}

	/*
	 * get worst piece possible for current board
	 */
	private Piece getWorst() {
		Piece[] pieces = new Piece[7];
		pieces = Piece.getPieces();
		Piece retPiece = null;
		double worstScore = 0.0;
		Brain.Move move = new Brain.Move();
		for (int i = 0; i < 7; i++) {
			move = brain.bestMove(board, pieces[i], HEIGHT, move);
			if (move == null)
				return pieces[i];
			if (move.score > worstScore) {
				worstScore = move.score;
				retPiece = move.piece;
			}
		}
		return retPiece;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}

		JBrainTetris tetris = new JBrainTetris(16);
		JFrame frame = JTetris.createFrame(tetris);
		frame.setVisible(true);
	}

}
