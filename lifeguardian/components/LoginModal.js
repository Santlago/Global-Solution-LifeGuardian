import React, { useState } from 'react';
import Modal from 'react-modal';
import styles from './LoginModal.module.scss';

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

const LoginModal = ({ isOpen, onRequestClose }) => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const loginUser = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha }),
      });

      if (!response.ok) {
        throw new Error('Erro ao fazer login');
      }

      const data = await response.json();
      console.log('Resposta da API:', data);
      // Aqui você pode atualizar o estado da aplicação conforme necessário

      // Feche o modal após o login bem-sucedido
      onRequestClose();
    } catch (error) {
      // Lide com erros, por exemplo, exibindo uma mensagem de erro
      console.error('Erro ao fazer login:', error.message);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      contentLabel="Login Modal"
    >
      <div className={styles.modal}>
        <h2>Login</h2>
        <form className={styles.form} onSubmit={loginUser}>
          <label>Email:</label>
          <input
            type="email"
            placeholder="Digite seu e-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <label>Senha:</label>
          <input
            type="password"
            placeholder="Digite sua senha"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
          />

          <button type="submit">Entrar</button>
        </form>
      </div>
    </Modal>
  );
};

export default LoginModal;
