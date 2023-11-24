import React, { useState } from 'react';
import Modal from 'react-modal';
import styles from './CadastroModal.module.scss';
import CadastroMedicoModal from './CadastroMedicoModal';

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

const CadastroModal = ({ isOpen, onRequestClose }) => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [id, setId] = useState('');

  const [isCadastroMedicoModalOpen, setCadastroMedicoModalOpen] = useState(false);

  const openCadastroMedicoModal = () => {
    setCadastroMedicoModalOpen(true);
  };

  const closeCadastroMedicoModal = () => {
    setCadastroMedicoModalOpen(false);
  };

  const cadastrarUsuario = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('/api/cadastro', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha, id }),
      });

      if (!response.ok) {
        throw new Error('Erro ao cadastrar usuário');
      }

      const data = await response.json();
      console.log('Resposta da API:', data);
      // Aqui você pode atualizar o estado da aplicação conforme necessário

      // Feche o modal após o cadastro bem-sucedido
      onRequestClose();
    } catch (error) {
      // Lide com erros, por exemplo, exibindo uma mensagem de erro
      console.error('Erro ao cadastrar usuário:', error.message);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      contentLabel="Cadastro Modal"
    >
      <div className={styles.modal}>
        <h2>Cadastro</h2>
        <form className={styles.form} onSubmit={cadastrarUsuario}>
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

          <label>ID:</label>
          <input
            type="text"
            placeholder="Digite seu ID"
            value={id}
            onChange={(e) => setId(e.target.value)}
          />

          <button type="submit">Enviar</button>
        </form>

        {/* Botão para abrir o modal de cadastro de médico */}
        <button onClick={openCadastroMedicoModal} className={styles.cadastroMedicoButton}>
          Cadastro Médico Especializado
        </button>
      </div>

      {/* Modal de Cadastro de Médico Especializado */}
      <CadastroMedicoModal
        isOpen={isCadastroMedicoModalOpen}
        onRequestClose={closeCadastroMedicoModal}
      />
    </Modal>
  );
};

export default CadastroModal;
