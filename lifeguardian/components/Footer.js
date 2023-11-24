import React from 'react';
import styles from '../components/_footer.module.scss'

const Footer = () => {
  return (
    <footer>
      <div className={styles.footerleft}>
        

        <div className={styles.footerlogo}>
        <img src="/logo-color.jpg" alt="LifeGuardian Porto" />
        <h3>Projeto Life Guardian</h3>
        </div>

        <h2>Equipe:</h2>
        <br />
        <p>RM99558: Francineldo Luan Martins Alvelino - 1TDSPI - Desenvolvedor frontend</p>
        <p>RM99067: Daniel dos Santos Araujo Faria - 1TDSPK - Documentação</p>
        <p>RM552270: Breno Lemes Santiago - 1TDSPK - Desenvolvedor Backend Java</p>
        <p>RM552344: Vitória Maria de Camargo - 1TDSPK - Banco de dados</p>
        <p>RM551154: Mariana Trentino Albano - 1TDSPM - Desenvolvedora Python</p>
      </div>
      
    </footer>
  );
};

export default Footer;