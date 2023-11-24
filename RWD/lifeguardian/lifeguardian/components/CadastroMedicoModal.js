import React, { useState } from 'react';
import Modal from 'react-modal';
import styles from './CadastroMedicoModal.module.scss';

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

const CadastroMedicoModal = ({ isOpen, onRequestClose }) => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [crm, setCrm] = useState('');
  const [especialidade, setEspecialidade] = useState('');
  const [formacao, setFormacao] = useState('');

  const cadastrarMedico = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('/api/cadastroMedico', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nome, email, senha, crm, especialidade, formacao }),
      });

      if (!response.ok) {
        throw new Error('Erro ao cadastrar médico');
      }

      const data = await response.json();
      console.log('Resposta da API:', data);
      
      onRequestClose();
    } catch (error) {
     
      console.error('Erro ao cadastrar médico:', error.message);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      style={customStyles}
      contentLabel="Cadastro Médico Modal"
    >
      <div className={styles.modal}>
        <h2>Cadastro de Médico Especializado</h2>
        <form className={styles.form} onSubmit={cadastrarMedico}>
          <label>Nome:</label>
          <input
            type="text"
            placeholder="Digite seu nome"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
          />

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

          <label>CRM:</label>
          <input
            type="text"
            placeholder="Digite seu CRM"
            value={crm}
            onChange={(e) => setCrm(e.target.value)}
          />

          <label>Especialidade:</label>
          <input
            type="text"
            placeholder="Digite a especialidade"
            value={especialidade}
            onChange={(e) => setEspecialidade(e.target.value)}
          />

          <label>Formação:</label>
          <input
            type="text"
            placeholder="Digite sua formação"
            value={formacao}
            onChange={(e) => setFormacao(e.target.value)}
          />

          <button type="submit">Enviar</button>
        </form>
      </div>
    </Modal>
  );
};

export default CadastroMedicoModal;
