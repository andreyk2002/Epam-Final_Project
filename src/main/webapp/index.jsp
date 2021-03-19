<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name ="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film rating</title>
    <link href="static/css/reset.css" rel="stylesheet"/>
    <link href="static/css/main.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="WEB-INF/view/fragments/header.jsp"/>
<main>
    <section class="intro">
        <div class="wrapper">
            <h1 class="into-title">Perfect place for film lovers</h1>
            <p class="into-subtitle">
                Share your film experience and emotions with other users,
                leave helpful reviews and earn rating points to become true film expert!
            </p>
            <form class="login-form" action="${pageContext.request.contextPath}/controller" method="post">
                <fieldset class="login-form-wrap">
                    <p class="form-info">
                        <input type="hidden" name="commandName" value="login"/>
                        <input class="login-form-input" type="text" name="username" placeholder="username"/>
                        <input class="login-form-input" type="password" name="password"
                               placeholder="password"/>
                        <button class="login-submit" type="submit">login</button>
                    </p>
                </fieldset>
            </form>
        </div>
    </section>
    <section class="benefits">
        <div class="benefits-wrapper">
            <h2 class="benefit-title"> Why your should try our site?</h2>
            <div class="benefits-cards">
                <div class="card">
                    <div class="img-card">
                        <img src="static/img/bootstrap-icons-1.4.0/film.svg"/>
                    </div>
                    <h3 class="benefits-card-title">
                        Welcome to TopFilms!
                    </h3>
                    <p class="benefits-info">
                        Site where you cant find a lot of films and see users impressions and opinions on them
                    </p>
                    <a class="benefit-reg" href="${pageContext.request.contextPath}/controller?commandName=registerPage">Register now!</a>
                </div>
                <div class="card">
                    <div class="img-card">
                        <img src="static/img/bootstrap-icons-1.4.0/star-fill.svg" alt=""/>
                    </div>
                    <h3 class="benefits-card-title">
                        Rate films and earn your own rating!
                    </h3>
                    <p class="benefits-info">
                        Interesting rating system not only for films,
                        but also for file reviewers
                    </p>
                    <a class="benefit-reg" href="${pageContext.request.contextPath}/register">Register now!</a>
                </div>
                <div class="card">
                    <div class="img-card">
                        <img class="card-img" src="static/img/bootstrap-icons-1.4.0/chat.svg"/>
                    </div>
                    <h3 class="benefits-card-title">
                        Communicate with others films lovers!
                    </h3>
                    <p class="benefits-info">
                        Share your emotions and experience in reviews
                    </p>
                    <a class="benefit-reg" href="${pageContext.request.contextPath}/register">Register now!</a>
                </div>
            </div>
        </div>
    </section>
    <section class="films-types">
        <div class="wrapper-wide">
            <h2 class="films-types-title sections-title">
                Chose between different genres
            </h2>
            <div class="types-images">
                <figure class="types-image size-lg">
                    <img class="type-image" src="static/img/science_fiction.jpg" alt="science fiction"/>
                    <figcaption class="types-img-title">Science fiction</figcaption>
                </figure>
                <figure class="types-image size-lg">
                    <img class="type-image" src="static/img/comedy.jfif" alt="comedy"/>
                    <figcaption class="types-img-title">Comedy</figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/download.jfif" alt="action"/>
                    <figcaption class="types-img-title">Action film</figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/drama.jpg" alt="drama"/>
                    <figcaption class="types-img-title">Drama</figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/detective.jfif" alt="detective"/>
                    <figcaption class="types-img-title">Detective</figcaption>
                </figure>
            </div>
        </div>
    </section>
</main>
<jsp:include page="WEB-INF/view/fragments/footer.jsp"/>
</body>
</html>