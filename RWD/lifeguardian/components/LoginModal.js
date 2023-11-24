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

const LoginModal = ({ isOpen, onRequestClose, onSuccessfulLogin }) => {
  const [loginData, setLoginData] = useState({
    login: '',
    senha: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch('http://localhost:13895/br.com.fiap/rest/usuario', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          // You might need to include additional headers based on your API requirements
        },
      });
  
      if (!response.ok) {
        throw new Error('Erro ao fazer login');
      }
  
      // Assuming the response is JSON, you can extract the data like this:
      const userData = await response.json();
  
      // Now userData contains the JSON data returned by the server
      console.log('Dados do usuário:', userData);
  
      // Check if the login and password match any user in the database
      const matchingUser = userData.find(user => user.login === loginData.login && user.senha === loginData.senha);
  
      if (matchingUser) {
        // Login successful, perform actions after successful login
        console.log('Login efetuado com sucesso!');
        
        // Show a positive alert for successful login
        alert('Login efetuado com sucesso!');
        
        // Call the onSuccessfulLogin prop with user data
        onSuccessfulLogin(matchingUser);
        
        // Close the modal after successful login
        onRequestClose();
      } else {
        // Login failed, display an error message or handle it accordingly
        console.error('Erro ao fazer login! Credenciais inválidas');
        
        // Show an alert for unsuccessful login
        alert('Erro ao fazer login! Credenciais inválidas');

        // Clear the input fields
        setLoginData({ login: '', senha: '' });
      }
    } catch (error) {
      // Handle errors, for example, by displaying an error message
      console.error('Erro ao fazer login:', error.message);
  
      // Check if the error object has a response property
      if (error.response) {
        console.error('Server response:', await error.response.json()); // Log the server response
      }
  
      // Show an alert for other errors
      alert('Erro ao fazer login. Por favor, tente novamente.');
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
        <form className={styles.form} onSubmit={handleSubmit}>
          <label>Login:</label>
          <input
            type="text"
            name="login"
            placeholder="Digite seu login"
            value={loginData.login}
            onChange={handleChange}
          />

          <label>Senha:</label>
          <input
            type="password"
            name="senha"
            placeholder="Digite sua senha"
            value={loginData.senha}
            onChange={handleChange}
          />

          <button type="submit">Entrar</button>
        </form>
      </div>
    </Modal>
  );
};

export default LoginModal;
