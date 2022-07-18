<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/header.jsp" %>




<section class="section-more padding-small">
  <div class="container d-flex justify-content-between">
    <div class="mr-4">
      <h1 class="pb-3">Thats the about page</h1>
      <h4 class="pt-1">consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
        magna aliqua.</h4>
    </div>

  </div>
</section>

<section class="padding-small details bg-light">
  <div class="container">
    <div class="row">
      <div class="col text-center">
        <i class="fas fa-check icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
      <div class="col text-center pr-4 pl-4 mr-4 ml-4">
        <i class="far fa-clock icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
      <div class="col text-center">
        <i class="fas fa-list icon-details"></i>
        <h1>Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
    </div>
  </div>
</section>


<section class="newsletter-section padding-small">
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>Lorem ipsum dolor sie amet</h1>
      </div>
      <div class="col-5">
        <div class="input-group mb-3">
          <input type="text" class="form-control border-0 rounded-0" placeholder=""
                 aria-label="Recipient's username" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2">
              <a href="index.html">Lorem</a>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="padding-medium story bg-light" id="about">
  <div class="container d-flex justify-content-center align-items-center">
    <div class="row">
      <div class="col-4 mr-4">
        <div class="div-img">
        </div>
      </div>

      <div class="col-7 ml-4">
        <h1 class="pb-1">Lorem ipsum dolor sit amet</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat
          volutpat. Donec placerat nisl magna, et faucibus arcu condimentum sed. Lorem ipsum dolor sit
          amet, consectetur adipiscing elit. Aliquam at porttitor sem. Aliquam erat volutpat. Donec
          placerat nisl magna, et faucibus arcu condimentum sed.
        </p>
      </div>
    </div>
  </div>
</section>

<section class="last-info-section padding-small" id="contact">
  <div class="container">
    <div class="row">
      <div class="col">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at porttitor sem.  Aliquam erat volutpat. Donec placerat nisl magna.</p>
      </div>
      <div class="col pl-4 ml-4">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <ul class="container">
          <li>consectetur adipiscing elit</li>
          <li>sed do eiusmod tempor</li>
          <li>incididunt ut labore</li>
          <li>et dolore magna aliqua</li>
        </ul>
      </div>
      <div class="col">
        <h3 class="mb-4">Lorem ipsum dolor</h3>
        <div class="input-group mb-3">
          <input type="text" class="form-control border-0 rounded-0" placeholder=""
                 aria-label="Recipient's username" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="input-group-text btn-color border-0 rounded-0" type="submit" id="basic-addon2"><a
                    href="index.html">Lorem</a></button>
          </div>
        </div>
        <div class="container d-flex-row">
          <a href="#">
            <i class="fab fa-facebook-square mr-4 icon-social"></i>
          </a>
          <a href="#">
            <i class="fab fa-twitter-square mr-4 icon-social"></i>

          </a>
          <a href="#">
            <i class="fab fa-instagram icon-social"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<%@ include file="/footer.jsp" %>
