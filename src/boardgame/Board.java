package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	//Gera��o do tabuleiro
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Erro criando tabuleiro: � necess�rio que haja pelo menos 1 linha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Posicao n�o est� no tabuleiro");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posicao n�o est� no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//Aloca��o da pe�a
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("H� uma pe�a na posi��o " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//Retirada de pe�a ja existente
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posicao n�o est� no tabuleiro");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	//Filtragem
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posicao n�o est� no tabuleiro");
		}
		return piece(position) != null;
	}
}
