package br.com.cidade.model.Pais.dao;

public class DAOException extends Exception {

        public DAOException(String message) {
            super(message);
        }

        public DAOException(String message, Exception cause) {
            super(message, cause);
        }
}
