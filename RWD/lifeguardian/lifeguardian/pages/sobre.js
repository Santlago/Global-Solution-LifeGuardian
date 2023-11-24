
import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import styles from '../styles/sobre.module.scss'

const SobrePage = () => {
  return (
    <div>

        <Header/>
      
      <div className={styles.containerpage}>
        <div className={styles.sessão1}>
            <h1>Sobre o projeto:</h1>
          <p>
          O Life Guardian foi criada com o objetivo reduzir a taxa de mortalidade infantil entre crianças recém nascidas até os 5 anos, utilizando tecnologia para tornar os cuidados mais eficientes em situações de risco. Propomos a criação de um website atualizado com guias ações de primeiros socorros para os responsavéis em situações emergenciais afim de ajudar a reduzir as mortes evitáveis principalmente em acidentes domésticos.<br/><br/> A educação dos pais e cuidadores sobre práticas seguras em casa, juntamente com o conhecimento de primeiros socorros, desempenha um papel crucial na prevenção de acidentes e na promoção da segurança infantil, e é exatamente isso que o nosso projeto busca melhorar.
          </p>
        </div>

        <div className={styles.img}>
          <img src="/logo-color.jpg" alt="Imagem Sobre Nós" />
        </div>



        <div className={styles.sessão2}>
            <h1>Conheça nossa equipe!</h1>
          <p>
          Desenvolvedores do projeto.
          </p>
        </div>

        <section className={styles.section_limit}>
        <div className={styles.modelo_3}>
          <div className={styles.quadros}>

            <div className={styles.quadro}>
              <h1>Francineldo Luan</h1>
              <h2>1TDSPI</h2>
              <div className={styles.circulo}>
                <img src='/francis.jpg' alt="Francis" />
              </div>
              <h2>RM:99558</h2>
            </div>
            
            <div className={styles.quadro}>
              <h1>Vitória Maria</h1>
              <h2>1TDSPK</h2>
              <div className={styles.circulo}>
                <img src='/vitória.jpg' alt="vitoria" />
              </div>
              <h2>RM:552344</h2>
            </div>

            <div className={styles.quadro}>
              <h1>Mariana Trentino</h1>
              <h2>1TDSPM</h2>
              <div className={styles.circulo}>
                <img src='/Mariana.jpg' alt="Mariana" />
              </div>
              <h2>RM:551154</h2>
            </div>

            <div className={styles.quadro}>
              <h1>Breno Lemes</h1>
              <h2>1TDSPK</h2>
              <div className={styles.circulo}>
                <img src='/breno.jpg' alt="Breno" />
              </div>
              <h2>RM:552270</h2>
            </div>


            <div className={styles.quadro}>
              <h1>Daniel dos Santos Araujo Faria</h1>
              <h2>1TDSPK</h2>
              <div className={styles.circulo}>
                <img src='/daniel.jpeg' alt="Daniel" />
              </div>
              <h2>RM:99067</h2>
            </div>

          </div>
        </div>
      </section>

      </div>
      <Footer/>
    </div>
  );
};

export default SobrePage;
