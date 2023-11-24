// pages/dados.js
import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import styles from '../styles/dados.module.scss';

const Dados = () => {
  return (
    <div>
      <Header />
      
      <section className={styles.Containerpage}>
        <div className={styles.sessão1}>
          <div>
            <h2>Dados Globais: </h2>
            <p>Segundo dados da OMS, em 2019, aproximadamente 5,2 milhões de crianças com menos de 5 anos morreram em todo o mundo. Este número representa uma redução significativa em comparação com as décadas anteriores, mas ainda há desafios consideráveis.

            Em muitos casos, as mortes em casa estão relacionadas a acidentes e situações de emergência que podem ser prevenidos com medidas adequadas de segurança e educação.</p>
          </div>
        </div>

        <div className={styles.sessão2}>
          <div>
            <h2>Emergencias Evitáveis</h2>
            <p>De acordo com organizações de saúde globais, uma parte significativa das mortes de crianças menores de 5 anos está associada a causas evitáveis, incluindo aquelas relacionadas a emergências domésticas. Isso inclui situações como engasgos, quedas, queimaduras, intoxicações e outros acidentes que podem ocorrer no ambiente doméstico</p>
          </div>
        </div>
      </section>

      <section className={styles.section_limit}>
        <div className={styles.modelo_3}>

          <div className={styles.quadros}>

            <div className={styles.quadro}>
              <div className={styles.emergencia}>
                <img src='/engasgo.jpg' alt="Engasgo" />
              </div>
              <p>efnbuefuwe</p>
              
              
            </div>
            
            <div className={styles.quadro}>
              <div className={styles.emergencia}>
                <img src='/parada.jpg' alt="parada" />
              </div>
              <p>ayufu7qaf</p>
            </div>

            <div className={styles.quadro}>
              <div className={styles.emergencia}>
                <img src='/lesão.jpg' alt="lesão na cabeça" />
              </div>
              <p>aubqf</p>
              
            </div>

            

          </div>
        </div>
      </section>


      <Footer />
    </div>
  );
};

export default Dados;
