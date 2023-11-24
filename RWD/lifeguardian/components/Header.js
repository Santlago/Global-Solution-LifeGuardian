// components/Header.js
import React, { useState } from 'react';
import styles from '../components/_header.module.scss';
import Link from 'next/link';
import LoginModal from './LoginModal';
import CadastroModal from './CadastroModal';

const Header = () => {
  const [isLoginModalOpen, setLoginModalOpen] = useState(false);
  const [isCadastroModalOpen, setCadastroModalOpen] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState(null);

  const openLoginModal = () => {
    setLoginModalOpen(true);
  };

  const closeLoginModal = () => {
    setLoginModalOpen(false);
  };

  const openCadastroModal = () => {
    setCadastroModalOpen(true);
  };

  const closeCadastroModal = () => {
    setCadastroModalOpen(false);
  };

  const handleSuccessfulLogin = (userData) => {
    setLoggedInUser(userData);
  };

  const handleLogout = () => {
    setLoggedInUser(null);
  };

  return (
    <header>
      <nav className={styles.nav}>
        <ul>
          {/* Conditional rendering based on login state */}
          {loggedInUser ? (
            <>
              <li>
                <span>{`Bem-vindo, ${loggedInUser.login}!`}</span>
                <button onClick={handleLogout}>Logout</button>
              </li>
            </>
          ) : (
            <>
              {/* Botão de login que abre o modal */}
              <li><a href="#" onClick={openLoginModal}>Login</a></li>

              {/* Botão de cadastro que abre o modal de cadastro */}
              <li><a href="#" onClick={openCadastroModal}>Cadastro</a></li>
            </>
          )}

          {/* Link para a Página Inicial */}
          <li>
            <Link href="/">
              <div className={styles.logo}>
                <img src="/logo.png" alt="Logo LifeGuardian" />
              </div>
            </Link>
          </li>

          {/* Link para a página de Dados */}
          <li><Link href="/dados">Dados</Link></li>

          {/* Link para a página Sobre Nós */}
          <li><Link href="/sobre">Sobre nós</Link></li>
        </ul>
      </nav>

      {/* Modal de Login */}
      <LoginModal
        isOpen={isLoginModalOpen}
        onRequestClose={closeLoginModal}
        onSuccessfulLogin={handleSuccessfulLogin}
      />

      {/* Modal de Cadastro */}
      <CadastroModal
        isOpen={isCadastroModalOpen}
        onRequestClose={closeCadastroModal}
      />
    </header>
  );
};

export default Header;
