//------------------------------------------------------------------NOVO----------------------------------------------------------
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
  const [usuario, setUsuario] = useState({
    email:'',
    login:'',
    senha:''
  })
  const [pessoa, setPessoa] = useState({
    nome:'',
    cpf:'',
    telefone:'',
    idade:'',
    genero:''
  })
  const [endereco, setEndereco] = useState({
    cep:'',
    numero:''
  })

  const handleChange = (e) => {
    const { name, value } = e.target;
  
    // Convert "idade" to an integer if the name is "idade"
    const parsedValue = name === "idade" ? parseInt(value, 10) : value;
  
    setUsuario((prevUsuario) => ({ ...prevUsuario, [name]: parsedValue }));
    setPessoa((prevPessoa) => ({ ...prevPessoa, [name]: parsedValue }));
    setEndereco((prevEndereco) => ({ ...prevEndereco, [name]: parsedValue }));
  };  

  const [isCadastroMedicoModalOpen, setCadastroMedicoModalOpen] = useState(false);

  const openCadastroMedicoModal = () => {
    setCadastroMedicoModalOpen(true);
  };

  const closeCadastroMedicoModal = () => {
    setCadastroMedicoModalOpen(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      // Create a new object with only the necessary properties for /usuario endpoint
      const usuarioData = {
        email: usuario.email,
        login: usuario.login,
        senha: usuario.senha,
        autenticado: 'ATIVO'
      };
  
  
      // Step 1: Post to /usuario
      const usuarioResponse = await fetch('http://localhost:13895/br.com.fiap/rest/usuario', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuarioData),
      });

      const usuarioResponseData = await usuarioResponse.json();
      console.log("usuarioResponseData:", usuarioResponseData)
      const usuarioId = usuarioResponseData;
    
      // Now you can use usuarioId as needed
      console.log('Usuario ID:', usuarioId);


      // Create a new object with only the necessary properties for /pessoa endpoint
      const pessoaData = {
        nome: pessoa.nome,
        cpf: pessoa.cpf,
        telefone: pessoa.telefone,
        idade: pessoa.idade,
        genero: pessoa.genero,
        usuario: {
          id: usuarioId,
          email: usuario.email,
          login: usuario.login,
          senha: usuario.senha,
          autenticado: 'ATIVO'
        }
      };
  
      // Step 2: Post to /pessoa
      const pessoaResponse = await fetch('http://localhost:13895/br.com.fiap/rest/pessoa', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pessoaData),
      });
  
      const pessoaResponseData = await pessoaResponse.json();
      console.log("pessoaResponseData:", pessoaResponseData)
      const pessoaId = pessoaResponseData;

      // Now you can use usuarioId as needed
      console.log('Pessoa ID:', pessoaId);
  
      // Create a new object with only the necessary properties for /endereco endpoint
      const enderecoData = {
        cep: endereco.cep,
        numero: endereco.numero,
        pessoa: {
          id: pessoaId,
          nome: pessoa.nome,
          cpf: pessoa.cpf,
          telefone: pessoa.telefone,
          idade: pessoa.idade,
          genero: pessoa.genero,
          usuario: {
            id: usuarioId,
            email: usuario.email,
            login: usuario.login,
            senha: usuario.senha,
            autenticado: 'ATIVO'
          }
        }
      };
  
      // Step 3: Post to /endereco
      const enderecoResponse = await fetch('http://localhost:13895/br.com.fiap/rest/endereco', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(enderecoData),
      });
  
  
      const enderecoResponseData = await enderecoResponse.json();
      console.log("enderecoResponseData:", enderecoResponseData)
      const enderecoId = enderecoResponseData;
      console.log("enderecoId:", enderecoId)
  
      // All requests successful, you can perform additional actions if needed
  
      // Close the modal after successful registration
      onRequestClose();
    } catch (error) {
      // Handle errors, for example, by displaying an error message
      console.error('Erro ao cadastrar:', error.message);
  
      // Check if the error object has a response property
      if (error.response) {
        console.error('Server response:', await error.response.json()); // Log the server response
      }
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
        <form className={styles.form} onSubmit={handleSubmit}>
        <label>Email:</label>
        <input
          type="email"
          name="email"
          placeholder="Digite seu e-mail"
          value={usuario.email}
          onChange={handleChange}
        />

        <label>Login:</label>
        <input
          type="text"
          name="login"
          placeholder="Digite seu login"
          value={usuario.login}
          onChange={handleChange}
        />

        <label>Senha:</label>
        <input
          type="password"
          name='senha'
          placeholder="Digite sua senha"
          value={usuario.senha}
          onChange={handleChange}
        />

        <label>Nome:</label>
        <input
          type="text"
          name="nome"
          placeholder="Digite seu nome"
          value={pessoa.nome}
          onChange={handleChange}
        />

        <label>CPF:</label>
        <input
          type="text"
          name="cpf"
          placeholder="Digite seu CPF"
          value={pessoa.cpf}
          onChange={handleChange}
        />

        <label>Telefone:</label>
        <input
          type="text"
          name="telefone"
          placeholder="Digite seu telefone"
          value={pessoa.telefone}
          onChange={handleChange}
        />

        <label>Idade:</label>
        <input
          type="text"
          name="idade"
          placeholder="Digite sua idade"
          value={pessoa.idade}
          onChange={handleChange}
        />

        <label>Gênero:</label>
        <input
          type="text"
          name="genero"
          placeholder="Digite seu gênero"
          value={pessoa.genero}
          onChange={handleChange}
        />

        <label>CEP:</label>
        <input
          type="text"
          name="cep"
          placeholder="Digite seu CEP"
          value={endereco.cep}
          onChange={handleChange}
        />

        <label>Número:</label>
        <input
          type="text"
          name="numero"
          placeholder="Digite seu número"
          value={endereco.numero}
          onChange={handleChange}
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
