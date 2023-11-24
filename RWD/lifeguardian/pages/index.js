// pages/index.js
import Header from '../components/Header';
import Footer from '../components/Footer';
import React from 'react';
import styles from '../styles/index.module.scss';


const HomePage = () => {
  return (
    <div>
      <Header />

      <div className={styles.container}>
        <section className={styles.banner}>
          <div>
            <h3>Bem vindo a</h3>
            <h1>Life Guardian</h1>
            <h3>Amar é cuidar!</h3>
          </div>
        </section>
      </div>

      <section className={styles.Containerpage}>
        <div className={styles.sessão1}>
          <div>
            <h2>Sobre a mortalidade infantil</h2>
            <p> As principais causas de mortalidade em crianças recém-nascidas até 5 anos podem variar de acordo com a região do mundo, o nível socioeconômico e outros fatores. No entanto, algumas causas são comuns globalmente. É importante notar que avanços na medicina e práticas de saúde pública têm contribuído para a redução da mortalidade infantil. </p>
          </div>
        </div>

        <div className={styles.sessão2}>
          <div>
            <h2>Projeto Life Guardian</h2>
            <p> Nesse contexto, o projeto proposto pelo nosso grupo tem como objetivo reduzir a taxa de mortalidade infantil entre crianças recém nascidas até os 5 anos, utilizando tecnologia para tornar os cuidados mais eficientes em situações de risco. Propomos a criação de um website atualizado com guias ações de primeiros socorros para os responsavéis em situações emergenciais. Porem é importante ressaltar que o acionamento de servições de emergência como o SAMU (192) e os bombeiros (193) é crucial. </p>
          </div>
        </div>
      </section>

      <div>
        <h1>Emergencias Frequentes:</h1>
      </div>


      <section className={styles.section_limit}>
        <div className={styles.modelo_3}>
          <div className={styles.quadros}>

            <div className={styles.quadro}>
              <h1>Engasgo</h1>
              <h2>O que fazer?</h2>
              <div className={styles.emergencia}>
                <img src='/engasgo.jpg' alt="Engasgo" />
              </div>
              
            </div>
            
            <div className={styles.quadro}>
              <h1>Parada Respiratória</h1>
              <h2>O que fazer?</h2>
              <div className={styles.emergencia}>
                <img src='/parada.jpg' alt="parada" />
              </div>
            </div>

            <div className={styles.quadro}>
              <h1>Lesão na cabeça</h1>
              <h2>O que fazer?</h2>
              <div className={styles.emergencia}>
                <img src='/lesão.jpg' alt="lesão na cabeça" />
              </div>
              
            </div>

            <div className={styles.quadro}>
              <h1>Queimadura</h1>
              <h2>O que fazer?</h2>
              <div className={styles.emergencia}>
                <img src='/queimadura.jpg' alt="Queimadura" />
              </div>
              
            </div>

          </div>
        </div>
      </section>

      <Footer />
    </div>
  );
};

export default HomePage;
